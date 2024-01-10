package com.okuma.dostu.backend.business.dtos.requests.favorite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFavoriteRequest {
    @NonNull
    private int bookId;
}
