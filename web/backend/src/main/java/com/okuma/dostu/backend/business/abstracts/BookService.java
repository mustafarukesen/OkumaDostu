package com.okuma.dostu.backend.business.abstracts;

import com.okuma.dostu.backend.business.dtos.requests.books.CreateBookRequest;
import com.okuma.dostu.backend.business.dtos.responses.books.CreatedBookResponse;
import com.okuma.dostu.backend.business.dtos.responses.books.GetAllBookResponse;

import java.util.List;

public interface BookService {

    List<GetAllBookResponse> getAll();
    CreatedBookResponse add(CreateBookRequest createBookRequest);
}
