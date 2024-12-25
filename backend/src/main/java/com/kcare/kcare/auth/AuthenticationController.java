package com.kcare.kcare.auth;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication Controller ", description = "Handles user authentication and account management")
public class AuthenticationController {

    private final AuthenticationService service;

    @Operation(summary = "Register a new user", description = "Registers a new user with the provided registration details and sends a confirmation Otp on Email")
    @PostMapping("/Register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> register(@RequestBody @Valid RegistrationRequest request)
            throws MessagingException, IOException {
        return ResponseEntity.ok(service.register(request));
    }

    @Operation(summary = "Verify OTP", description = "Verifies the OTP provided by the user.")
    @PostMapping("/Otp-verficiation")
    public ResponseEntity<String> otpVerification(@RequestBody OtpVerficationRequest otpVerficationRequest) {
        return ResponseEntity.ok(service.otpVerification(otpVerficationRequest));

    }

    @Operation(summary = "Authenticate a user", description = "Authenticates a user with the provided credentials and returns an authentication token.")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticaionRequest request)
            throws MessagingException, IOException {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @Operation(summary = "Resend OTP", description = "Resends the OTP to the user's email")

    @PostMapping("/resendOtp")
    public ResponseEntity<String> resendOtp(@RequestBody ResendOtpRequest resendOtpRequest)
            throws MessagingException, IOException {
        return ResponseEntity.ok(service.resendOtp(resendOtpRequest));
    }

    @Operation(summary = "Initiate Password Reset", description = "Initiates the password reset process by sending a reset link to the user's email.")

    @PostMapping("/initiatePasswordReset")
    public ResponseEntity<String> forgetPasword(@RequestBody ForgetPasswordRequest forgetPassword)
            throws MessagingException, IOException {

        return ResponseEntity.ok(service.forgetPassword(forgetPassword));
    }

    @Operation(summary = "Set New Password", description = "Sets a new password using the provided reset token and new password.")

    @PatchMapping("/setNewPasword")
    public ResponseEntity<String> resetForgottendPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {

        return ResponseEntity.ok(service.resetForgottendPassword(resetPasswordRequest));

    }

}
