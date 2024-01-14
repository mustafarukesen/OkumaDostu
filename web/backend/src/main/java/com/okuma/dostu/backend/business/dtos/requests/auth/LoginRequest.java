package com.okuma.dostu.backend.business.dtos.requests.auth;

import com.okuma.dostu.backend.business.messages.validation.AuthenticationValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotNull(message = AuthenticationValidationMessages.notNullEmail)
    @NotBlank(message = AuthenticationValidationMessages.notBlankEmail)
    private String email;

    @NotNull(message = AuthenticationValidationMessages.notNullPassword)
    @NotBlank(message = AuthenticationValidationMessages.notBlankPassword)
    private String password;
}
