package com.okuma.dostu.backend.business.dtos.responses.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedBookResponse {
    private int id;
    private int authorId;
    private int categoryId;
    private int publisherId;
    private int pageCount;
    private LocalDate publishedDate;
    private String title;
    private String description;
    private String isbn13;
    private String thumbnail;
}
