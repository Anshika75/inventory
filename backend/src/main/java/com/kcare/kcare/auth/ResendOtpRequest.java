package com.kcare.kcare.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResendOtpRequest {

    @NotNull(message = "ContactNumber should not be null")
    @NotEmpty(message = "ContactNumber should not be empty")
    @Email(message = "Please provide the valid email")
    private String email;

}
