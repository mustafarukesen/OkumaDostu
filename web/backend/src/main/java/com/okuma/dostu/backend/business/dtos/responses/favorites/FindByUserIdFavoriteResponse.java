package com.okuma.dostu.backend.business.dtos.responses.favorites;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FindByUserIdFavoriteResponse {
    private List<Integer> bookId;
}
