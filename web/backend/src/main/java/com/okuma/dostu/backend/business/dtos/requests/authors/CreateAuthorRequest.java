package com.okuma.dostu.backend.business.dtos.requests.authors;

import com.okuma.dostu.backend.business.messages.validation.AuthorValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAuthorRequest {
    @NotNull(message = AuthorValidationMessages.notNullName)
    @NotBlank(message = AuthorValidationMessages.notBlankName)
    private String name;
}
