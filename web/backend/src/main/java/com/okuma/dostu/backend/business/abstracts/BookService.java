package com.okuma.dostu.backend.business.abstracts;

import com.okuma.dostu.backend.business.dtos.requests.books.CreateBookRequest;
import com.okuma.dostu.backend.business.dtos.requests.books.UpdateBookRequest;
import com.okuma.dostu.backend.business.dtos.responses.books.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    List<GetAllBookResponse> getAll();

    Page<GetAllBookResponse> getAllWithPagination(Pageable pageable);

    Page<GetByTitleBookResponse> getByTitle(String title, Pageable pageable);

    Page<GetByAuthorNameBookResponse> getByAuthorName(String authorName, Pageable pageable);

    GetByIdBookResponse getById(int id);


    CreatedBookResponse add(CreateBookRequest createBookRequest);

    UpdatedBookResponse update(UpdateBookRequest updateBookRequest);

    DeletedBookResponse delete(int id);
}
