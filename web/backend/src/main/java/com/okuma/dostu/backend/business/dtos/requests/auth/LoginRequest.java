package com.okuma.dostu.backend.business.dtos.requests.auth;

import com.okuma.dostu.backend.business.messages.validation.AuthenticationValidationMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @Email(message = AuthenticationValidationMessages.emailFormat)
    @NotNull(message = AuthenticationValidationMessages.notNullEmail)
    @NotBlank(message = AuthenticationValidationMessages.notBlankEmail)
    private String email;

    @NotNull(message = AuthenticationValidationMessages.notNullPassword)
    @NotBlank(message = AuthenticationValidationMessages.notBlankPassword)
    @Size(min = 8, message = AuthenticationValidationMessages.passwordSize)
    private String password;
}
