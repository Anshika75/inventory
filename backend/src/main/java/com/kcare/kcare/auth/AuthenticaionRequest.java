package com.kcare.kcare.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticaionRequest {

    @NotBlank(message = "Email is mandatory")
    @NotEmpty(message = "Email is mandatory")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotEmpty(message = "Password is mandatory")
    @NotBlank(message = "password is mandatory")
    @Size(min = 8, message = "Password should be minimum 8 character long")
    private String password;

}
