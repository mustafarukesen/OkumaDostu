package com.okuma.dostu.backend.business.dtos.requests.auth;

import com.okuma.dostu.backend.business.messages.validation.AuthenticationValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangePasswordRequest {
    @NotNull(message = AuthenticationValidationMessages.notNullCurrentPassword)
    @NotBlank(message = AuthenticationValidationMessages.notBlankCurrentPassword)
    private String currentPassword;

    @NotNull(message = AuthenticationValidationMessages.notNullNewPassword)
    @NotBlank(message = AuthenticationValidationMessages.notBlankNewPassword)
    private String newPassword;

    @NotNull(message = AuthenticationValidationMessages.notNullConfirmationPassword)
    @NotBlank(message = AuthenticationValidationMessages.notBlankConfirmationPassword)
    private String confirmationPassword;
}
