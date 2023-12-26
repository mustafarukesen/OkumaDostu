package com.okuma.dostu.backend.business.abstracts;

import com.okuma.dostu.backend.business.dtos.requests.authors.CreateAuthorRequest;
import com.okuma.dostu.backend.business.dtos.responses.authors.CreatedAuthorResponse;
import com.okuma.dostu.backend.business.dtos.responses.authors.GetAllAuthorResponse;

import java.util.List;

public interface AuthorService {
    List<GetAllAuthorResponse> getAll();

    CreatedAuthorResponse add(CreateAuthorRequest createAuthorRequest);
}
