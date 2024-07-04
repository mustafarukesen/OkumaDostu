package com.okuma.dostu.backend.business.dtos.requests.auth;

import com.okuma.dostu.backend.business.messages.validation.AuthenticationValidationMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {
    @NotNull(message = AuthenticationValidationMessages.notNullFirstName)
    @NotBlank(message = AuthenticationValidationMessages.notBlankFirstName)
    private String firstName;

    @NotNull(message = AuthenticationValidationMessages.notNullLastName)
    @NotBlank(message = AuthenticationValidationMessages.notBlankLastName)
    private String lastName;

    @Email(message = AuthenticationValidationMessages.emailFormat)
    @NotNull(message = AuthenticationValidationMessages.notNullEmail)
    @NotBlank(message = AuthenticationValidationMessages.notBlankEmail)
    private String email;

    @NotNull(message = AuthenticationValidationMessages.notNullPassword)
    @NotBlank(message = AuthenticationValidationMessages.notNullPassword)
    @Size(min = 8, message = AuthenticationValidationMessages.passwordSize)
    private String password;
}
