package com.kcare.kcare.auth;

import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kcare.kcare.Email.EmailService;
import com.kcare.kcare.Email.EmailTemplateName;
import com.kcare.kcare.Role.Role;
import com.kcare.kcare.Role.RoleRepository;
import com.kcare.kcare.Role.URole;
import com.kcare.kcare.User.JwtService;
import com.kcare.kcare.User.Token;
import com.kcare.kcare.User.TokenRepository;
import com.kcare.kcare.User.User;
import com.kcare.kcare.User.Userrepository;
import com.kcare.kcare.common.Response;
import com.kcare.kcare.handler.AccountNotVerfiedException;
import com.kcare.kcare.handler.DuplicateEmailException;
import com.kcare.kcare.handler.PasswordNotMatchException;
import com.kcare.kcare.handler.ResourceNotFoundException;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
// import okhttp3.OkHttpClient;

@Service
@Builder
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Slf4j
public class AuthenticationService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Userrepository userrepository;

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired

    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private EmailService mailSender;

    // private final OkHttpClient client = new OkHttpClient();

    public Response<RegistrationRequest> register(RegistrationRequest request) throws MessagingException, IOException {

        if (userrepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException(request.getEmail() + " already Exist");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .build();

        Role role = roleRepository.findByName(URole.ROLE_ADMIN).orElseGet(() -> {

            Role patientRole = new Role();
            patientRole.setName(URole.ROLE_ADMIN);
            return roleRepository.save(patientRole);

        });

        user.setRole(role);

        role.addUser(user);
        userrepository.save(user);
        sendValidationEmail(user);

        return new Response<>(
                request,
                LocalDateTime.now(),
                "sucessfully registered",
                HttpStatus.CREATED

        );

    }

    private String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationCode(6);
        Token token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .verified(false)
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }

    private void sendValidationEmail(User user) throws MessagingException {

        String newToken = generateAndSaveActivationToken(user);
        mailSender.sendEmail(
                user.getEmail(),
                user.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                // activationUrl,
                newToken,
                "Account Activation");

    }

    // private void sendValidationEmail(User user) throws MessagingException,
    // IOException {
    // String otp = generateAndSaveActivationToken(user);
    // // sendSms(otp, user.getContactNumber());
    // }

    // public void sendSms(String otp, String email) throws IOException {
    // String authKey = "8352f3d95c43bde89bf39a38c37685a";
    // String senderId = "MDANTK";
    // String routeId = "1";
    // String message = String.format(
    // "Please use the code %s to log in on the Nodex App. Please do not share this
    // code with anyone for security reasons. For more detail visit
    // https://medantrik.com/ Team Medantrik",
    // otp);
    // String smsContentType = "English";
    // String templateId = "OTP";

    // String url = String.format(
    // "https://msg.msgclub.net/rest/services/sendSMS/sendGroupSms?AUTH_KEY=%s&senderId=%s&routeId=%s&message=%s&mobileNos=%s&smsContentType=%s&templateid=%s",
    // authKey, senderId, routeId, message, email, smsContentType, templateId);

    // Request request = new Request.Builder()
    // .url(url)
    // .get()
    // .addHeader("Cache-Control", "no-cache")
    // .build();

    // try (Response response = client.newCall(request).execute()) {
    // if (response.isSuccessful()) {
    // System.out.println(response.body().string());
    // } else {
    // throw new RuntimeException("Failed to send SMS: " + response.code());
    // }
    // }
    // }

    public AuthenticationResponse authenticate(@Valid AuthenticaionRequest request)
            throws MessagingException, IOException {

        User user1 = userrepository.findByEmail(request.getEmail())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong Email "));

        String password = user1.getPassword();

        if (!passwordEncoder.matches(request.getPassword(), password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong Password");
        }

        if (!user1.isEnabled()) {
            sendValidationEmail(user1);
            throw new AccountNotVerfiedException(
                    "Account not verified. Please check your email for the verification code.");
        }

        List<Token> allSavedToken = tokenRepository.findAllByUserId(user1.getId());
        tokenRepository.deleteAll(allSavedToken);

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        var claims = new HashMap<String, Object>();
        var user = ((User) auth.getPrincipal());
        claims.put("fullName", user.getFullName());

        var jwtToken = jwtService.generateToken(claims, (User) auth.getPrincipal());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    // @Transactional

    public Response<ResendOtpRequest> resendOtp(ResendOtpRequest resendOtpRequest)
            throws MessagingException, IOException {
        User user = userrepository.findByEmail(resendOtpRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(
                        resendOtpRequest.getEmail() + " is Not Registered"));

        if (user.isEnabled()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account is already verified, Please login");
        }
        String otp = generateAndSaveActivationToken(user);
        // sendSms(otp, resendOtpRequest.getContactNumber());

        return new Response<>(
                resendOtpRequest,
                LocalDateTime.now(),
                "Otp send",
                HttpStatus.OK

        );

    }

    public void changeAuthenticationPassword(ChangePasswordRequest changePasssword, Authentication connnectedUser)
            throws BadRequestException {

        User user = (User) connnectedUser.getPrincipal();

        if (!passwordEncoder.matches(changePasssword.getCurrentPassword(), user.getPassword())) {
            throw new BadRequestException("Password Donot Match");
        }

        if (!(changePasssword.getNewPassword().equals(changePasssword.getConfirmationPassword()))) {
            throw new PasswordNotMatchException("Password Not Match");
        }
        user.setPassword(passwordEncoder.encode(changePasssword.getNewPassword()));
        userrepository.save(user);

    }

    public Response<ForgetPasswordRequest> forgetPassword(ForgetPasswordRequest forgetPassword)
            throws MessagingException, IOException {
        User user = userrepository.findByEmail(forgetPassword.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Not Registered"));
        String otp = generateAndSaveActivationToken(user);
        // sendSms(otp, forgetPassword.getContactNumber());
        // verifyOtpforPasswordReset(otp, forgetPassword.getContactNumber());

        return new Response<>(
                forgetPassword,
                LocalDateTime.now(),
                "sucessfully saved",
                HttpStatus.CREATED

        );
    }

    public Response<ResetPasswordRequest> resetForgottendPassword(ResetPasswordRequest resetPasswordRequest) {

        User user = userrepository.findByEmail(resetPasswordRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("user not Found"));

        List<Token> allsavedTokens = tokenRepository.findAllByUserId(user.getId());
        for (Token token : allsavedTokens) {
            if (!token.isVerified()) {
                throw new AccountNotVerfiedException("Not allowed to ResetPassword");
            }
        }
        user.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
        user.setEnabled(true);
        userrepository.save(user);

        return new Response<>(
                resetPasswordRequest,
                LocalDateTime.now(),
                "sucessfully reset",
                HttpStatus.CREATED

        );
    }

    @Scheduled(fixedRate = 60000)
    public void cleanUpExpiredTokens() {
        tokenRepository.deleteAllByExpiresAtBefore(LocalDateTime.now());
    }

    // @Scheduled(cron = "0 0/30 * * * *")
    // public void cleanUpRegisteredUser() {
    // // log.info("schedule is running : ");
    // List<User> users = userrepository.findAllByEnabled(false);
    // if (!users.isEmpty()) {
    // userrepository.deleteAll(users);
    // }
    // }

    public Response<OtpVerficationRequest> otpVerification(OtpVerficationRequest otpVerficationRequest) {

        Token savedToken = tokenRepository.findByToken(otpVerficationRequest.getOtp())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Otp"));

        User user = userrepository.findById(savedToken.getUser().getId())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid User"));
        if (!user.getEmail().equals(otpVerficationRequest.getEmail())) {
            throw new IllegalArgumentException("Invalid Otp");
        }

        List<Token> allSavedToken = tokenRepository.findAllByUserId(user.getId());
        for (Token token : allSavedToken) {
            token.setVerified(true);
        }
        tokenRepository.saveAll(allSavedToken);

        if (!user.isEnabled()) {
            user.setEnabled(true);
            userrepository.save(user);
        }

        return new Response<>(
                otpVerficationRequest,
                LocalDateTime.now(),
                "verified",
                HttpStatus.ACCEPTED

        );
    }
}
