package com.okuma.dostu.backend.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookWithCategoryDto {
    private int id;
    private String bookName;
    private String categoryName;
}
