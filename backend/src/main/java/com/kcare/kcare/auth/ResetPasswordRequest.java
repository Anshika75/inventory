package com.kcare.kcare.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ResetPasswordRequest {

    @NotBlank(message = "Contact Number should Not Blank")
    @NotEmpty(message = "contact Number should Not Empty")

    @Email(message = "Please provide the valid email")
    private String email;

    @NotBlank(message = "Password Number should Not Blank")
    @NotEmpty(message = "Password Number should Not Empty")
    @Size(min = 8, message = "Password should be 8 charcters long minimum")
    private String newPassword;

}