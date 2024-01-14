package com.okuma.dostu.backend.business.dtos.requests.categories;

import com.okuma.dostu.backend.business.messages.validation.CategoryValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryRequest {
    @NotNull(message = CategoryValidationMessages.notNullName)
    @NotBlank(message = CategoryValidationMessages.notBlankName)
    private String name;
}
