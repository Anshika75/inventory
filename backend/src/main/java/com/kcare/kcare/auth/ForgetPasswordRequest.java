package com.kcare.kcare.auth;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ForgetPasswordRequest {

    @Email(message = "Please provide valid email")
    private String email;

}
