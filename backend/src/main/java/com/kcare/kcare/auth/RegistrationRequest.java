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

public class RegistrationRequest {

    @NotBlank(message = "FirstName should not be Blank")
    @Size(min = 2, message = "Must contain more than 2 letters")
    @Size(max = 50, message = "limit exceeds")
    private String firstName;

    @NotBlank(message = "LastName should not be Blank")
    @Size(min = 2, max = 50, message = "Must contain more than 2 letters")
    @Size(max = 50, message = "limit exceeds")
    private String lastName;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Please provide a valid email address")
    @Size(max = 50, message = "Word limit exceeds")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 50, message = "Password should be between 8 and 50 characters long")
    private String password;

}
