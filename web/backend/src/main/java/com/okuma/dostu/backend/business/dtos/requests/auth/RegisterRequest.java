package com.okuma.dostu.backend.business.dtos.requests.auth;

import com.okuma.dostu.backend.business.messages.validation.AuthenticationValidationMessages;
import com.okuma.dostu.backend.core.security.user.Role;
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
public class RegisterRequest {
    @NotNull(message = AuthenticationValidationMessages.notNullFirstName)
    @NotBlank(message = AuthenticationValidationMessages.notBlankFirstName)
    private String firstName;

    @NotNull(message = AuthenticationValidationMessages.notNullLastName)
    @NotBlank(message = AuthenticationValidationMessages.notBlankLastName)
    private String lastName;

    @NotNull(message = AuthenticationValidationMessages.notNullEmail)
    @NotBlank(message = AuthenticationValidationMessages.notBlankEmail)
    private String email;

    @NotNull(message = AuthenticationValidationMessages.notNullPassword)
    @NotBlank(message = AuthenticationValidationMessages.notNullPassword)
    private String password;
    private Role role;
}
