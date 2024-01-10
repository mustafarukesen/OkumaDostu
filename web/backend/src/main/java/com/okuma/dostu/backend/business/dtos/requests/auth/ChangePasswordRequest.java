package com.okuma.dostu.backend.business.dtos.requests.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangePasswordRequest {
    @NotNull
    @NotBlank
    private String currentPassword;

    @NotNull
    @NotBlank
    private String newPassword;

    @NotNull
    @NotBlank
    private String confirmationPassword;
}
