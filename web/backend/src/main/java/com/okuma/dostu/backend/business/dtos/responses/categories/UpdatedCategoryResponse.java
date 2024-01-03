package com.okuma.dostu.backend.business.dtos.responses.categories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedCategoryResponse {
    private int id;
    private String name;
}
