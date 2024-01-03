package com.okuma.dostu.backend.business.abstracts;


import com.okuma.dostu.backend.business.dtos.requests.categories.CreateCategoryRequest;
import com.okuma.dostu.backend.business.dtos.requests.categories.UpdateCategoryRequest;
import com.okuma.dostu.backend.business.dtos.responses.categories.CreatedCategoryResponse;
import com.okuma.dostu.backend.business.dtos.responses.categories.DeletedCategoryResponse;
import com.okuma.dostu.backend.business.dtos.responses.categories.GetAllCategoryResponse;
import com.okuma.dostu.backend.business.dtos.responses.categories.UpdatedCategoryResponse;

import java.util.List;

public interface CategoryService {
    List<GetAllCategoryResponse> getAll();

    CreatedCategoryResponse add(CreateCategoryRequest createCategoryRequest);

    UpdatedCategoryResponse update(UpdateCategoryRequest updateCategoryRequest);

    DeletedCategoryResponse delete(int id);
}
