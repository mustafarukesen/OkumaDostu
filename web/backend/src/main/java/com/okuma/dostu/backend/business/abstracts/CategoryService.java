package com.okuma.dostu.backend.business.abstracts;


import com.okuma.dostu.backend.business.dtos.requests.categories.CreateCategoryRequest;
import com.okuma.dostu.backend.business.dtos.responses.categories.CreatedCategoryResponse;
import com.okuma.dostu.backend.business.dtos.responses.categories.GetAllCategoryResponse;

import java.util.List;

public interface CategoryService {
    List<GetAllCategoryResponse> getAll();

    CreatedCategoryResponse add(CreateCategoryRequest createCategoryRequest);
}
