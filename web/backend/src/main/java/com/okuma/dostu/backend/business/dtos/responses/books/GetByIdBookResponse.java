package com.okuma.dostu.backend.business.dtos.responses.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdBookResponse {
    private int id;
    private String authorName;
    private String categoryName;
    private String publisherName;
    private int pageCount;
    private LocalDate publishedDate;
    private String title;
    private String description;
    private String isbn13;
    private String thumbnail;
}
