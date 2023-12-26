package com.okuma.dostu.backend.business.dtos.requests.books;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {

    @NotNull
    private int authorId;

    @NotNull
    private int categoryId;

    @NotNull
    private String description;

    @NotNull
    private String isbn13;
    @NotNull
    private int pageCount;

    @NotNull
    private LocalDate publishedDate;

    @NotNull
    private int publisherId;

    @NotNull
    @NotBlank
    private String title;
    @NotNull
    private String thumbnail;

}
