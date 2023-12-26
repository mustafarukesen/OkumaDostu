package com.okuma.dostu.backend.business.dtos.responses.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllBookResponse {
    private int id;
    private int authorId;
    private String authorName;
    private int categoryId;
    private String categoryName;
    private int publisherId;
    private String publisherName;
    private int pageCount;
    private LocalDate publishedDate;
    private String title;
    private String description;
    private String isbn13;
    private String thumbnail;
}
