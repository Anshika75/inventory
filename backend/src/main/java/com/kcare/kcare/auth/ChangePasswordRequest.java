package com.kcare.kcare.auth;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangePasswordRequest {

    @Column(nullable = false)
    @NotBlank(message = "Current Password should not Blank")
    private String currentPassword;
    @Column(nullable = false)

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "newPassword should be 8 charcters long minimum")
    private String newPassword;
    @Column(nullable = false)
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "confirmationPassword should be 8 charcters long minimum")
    private String confirmationPassword;

}
