package com.okuma.dostu.backend.business.dtos.requests.auth;

import com.okuma.dostu.backend.business.messages.validation.AuthenticationValidationMessages;
import com.okuma.dostu.backend.core.utilities.validation.FileType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UpdateRequest {

    @NotNull(message = AuthenticationValidationMessages.notNullFirstName)
    @NotBlank(message = AuthenticationValidationMessages.notBlankFirstName)
    private String firstName;

    @NotNull(message = AuthenticationValidationMessages.notNullLastName)
    @NotBlank(message = AuthenticationValidationMessages.notBlankLastName)
    private String lastName;

    @NotNull(message = AuthenticationValidationMessages.notNullDateOfBirth)
    private LocalDate dateOfBirth;

    @FileType(types = {"jpeg", "png"})
    private String image;
}
