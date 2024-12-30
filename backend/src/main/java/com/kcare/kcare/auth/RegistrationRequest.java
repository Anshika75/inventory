package com.kcare.kcare.auth;

import jakarta.persistence.Column;
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
    @Size(min = 2, max = 50, message = "Must contain more than 2 letter")
    private String firstName;

    @NotBlank(message = "LastName should not be Blank")
    @Size(min = 2, max = 50, message = "Must contain more than 2 letter")
    private String lastName;

    @Column(name = "Email", unique = true)
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Please provide a valid email address")
    @Size(max = 50, message = "Word limit exceed")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 50, message = "Password should be 8 charcters long minimum")
    private String password;

}
