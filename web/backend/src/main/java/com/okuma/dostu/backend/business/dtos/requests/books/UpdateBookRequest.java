package com.okuma.dostu.backend.business.dtos.requests.books;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookRequest {
    @NotNull
    private int id;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    private String isbn13;

    @NotNull
    @PositiveOrZero
    private int pageCount;

    @NotNull
    private LocalDate publishedDate;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String thumbnail;

    @NotNull
    private int authorId;

    @NotNull
    private int categoryId;

    @NotNull
    private int publisherId;
}
