package com.okuma.dostu.backend.business.abstracts;

import com.okuma.dostu.backend.business.dtos.requests.books.CreateBookRequest;
import com.okuma.dostu.backend.business.dtos.requests.books.UpdateBookRequest;
import com.okuma.dostu.backend.business.dtos.responses.books.CreatedBookResponse;
import com.okuma.dostu.backend.business.dtos.responses.books.DeletedBookResponse;
import com.okuma.dostu.backend.business.dtos.responses.books.GetAllBookResponse;
import com.okuma.dostu.backend.business.dtos.responses.books.UpdatedBookResponse;

import java.util.List;

public interface BookService {

    List<GetAllBookResponse> getAll();

    CreatedBookResponse add(CreateBookRequest createBookRequest);

    UpdatedBookResponse update(UpdateBookRequest updateBookRequest);

    DeletedBookResponse delete(int id);
}
