package com.okuma.dostu.backend.business.abstracts;

import com.okuma.dostu.backend.business.dtos.requests.books.CreateBookRequest;
import com.okuma.dostu.backend.business.dtos.requests.books.UpdateBookRequest;
import com.okuma.dostu.backend.business.dtos.responses.books.*;

import java.util.List;

public interface BookService {

    List<GetAllBookResponse> getAll();

    List<GetByTitleBookResponse> getByTitle(String title);

    List<GetByBooksWithAuthorNameBookResponse> getByBooksWithAuthorName(String authorName);

    GetByIdBookResponse getById(int id);


    CreatedBookResponse add(CreateBookRequest createBookRequest);

    UpdatedBookResponse update(UpdateBookRequest updateBookRequest);

    DeletedBookResponse delete(int id);
}
