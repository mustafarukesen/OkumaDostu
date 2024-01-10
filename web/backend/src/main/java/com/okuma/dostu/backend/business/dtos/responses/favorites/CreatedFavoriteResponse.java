package com.okuma.dostu.backend.business.dtos.responses.favorites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedFavoriteResponse {
    private int favoriteId;
    private int bookId;
    private int userId;
}
