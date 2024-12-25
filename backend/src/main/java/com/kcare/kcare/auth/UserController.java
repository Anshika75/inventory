package com.kcare.kcare.auth;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/Users")
@AllArgsConstructor
@Tag(name = "Change Password")
public class UserController {

    @Autowired
    private final AuthenticationService authenticationService;

    @PatchMapping("/changePasssword")
    public ResponseEntity<?> changeAuthenticationPassword(@RequestBody ChangePasswordRequest changePasssword,
            Authentication connectedUser) throws BadRequestException {

        authenticationService.changeAuthenticationPassword(changePasssword, connectedUser);
        return ResponseEntity.accepted().build();
    }

}
