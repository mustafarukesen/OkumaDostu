package com.okuma.dostu.backend.business.dtos.requests.books;

import com.okuma.dostu.backend.business.messages.validation.BookValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {

    @NotNull(message = BookValidationMessages.notNullDescription)
    @NotBlank(message = BookValidationMessages.notBlankDescription)
    private String description;

    @NotNull(message = BookValidationMessages.notNullIsbn13)
    @NotBlank(message = BookValidationMessages.notBlankIsbn13)
    private String isbn13;

    @NotNull(message = BookValidationMessages.notNullPageCount)
    @Positive(message = BookValidationMessages.positivePageCount)
    private int pageCount;

    @NotNull(message = BookValidationMessages.notNullPublishedDate)
    private LocalDate publishedDate;

    @NotNull(message = BookValidationMessages.notNullTitle)
    @NotBlank(message = BookValidationMessages.notBlankTitle)
    private String title;

    @NotNull(message = BookValidationMessages.notNullThumbnail)
    @NotBlank(message = BookValidationMessages.notBlankThumbnail)
    private String thumbnail;

    @NotNull(message = BookValidationMessages.notNullAuthorId)
    @Positive(message = BookValidationMessages.positivePageCount)
    private int authorId;

    @NotNull(message = BookValidationMessages.notNullCategoryId)
    @Positive(message = BookValidationMessages.positiveCategoryId)
    private int categoryId;

    @NotNull(message = BookValidationMessages.notNullPublisherId)
    @Positive(message = BookValidationMessages.positivePublisherId)
    private int publisherId;

}
