package com.okuma.dostu.backend.business.dtos.requests.favorite;

import com.okuma.dostu.backend.business.messages.validation.FavoriteValidationMessages;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFavoriteRequest {
    @NotNull(message = FavoriteValidationMessages.notNullBookId)
    @Positive(message = FavoriteValidationMessages.positiveBookId)
    private int bookId;
}
