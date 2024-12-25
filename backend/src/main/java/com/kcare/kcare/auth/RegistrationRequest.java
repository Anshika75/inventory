package com.kcare.kcare.auth;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class RegistrationRequest {

    @NotNull(message = "FirstName should not be null")
    @NotBlank(message = "FirstName should not be Blank")
    @Size(min = 2, max = 50, message = "FirstName should be between 2 and 50 characters")
    private String firstName;

    @NotNull(message = "LastName should not be null")
    @NotBlank(message = "LastName should not be Blank")
    @Size(min = 2, max = 50, message = "LastName should be between 2 and 50 characters")
    private String lastName;

    @Column(name = "Email", unique = true)
    @NotNull(message = "Email should not be null")
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotEmpty(message = "Password is mandatory")
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password should be 8 charcters long minimum")
    private String password;

}
