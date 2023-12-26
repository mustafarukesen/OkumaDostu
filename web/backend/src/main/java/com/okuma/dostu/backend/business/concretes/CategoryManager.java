package com.okuma.dostu.backend.business.concretes;

import com.okuma.dostu.backend.business.abstracts.CategoryService;
import com.okuma.dostu.backend.business.dtos.requests.categories.CreateCategoryRequest;
import com.okuma.dostu.backend.business.dtos.responses.categories.CreatedCategoryResponse;
import com.okuma.dostu.backend.business.dtos.responses.categories.GetAllCategoryResponse;
import com.okuma.dostu.backend.core.utilities.mappers.ModelMapperService;
import com.okuma.dostu.backend.dataAccess.abstracts.CategoryRepository;
import com.okuma.dostu.backend.entities.concretes.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {

    CategoryRepository categoryRepository;
    ModelMapperService modelMapperService;

    @Override
    public List<GetAllCategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();

        List<GetAllCategoryResponse> result = categories.stream()
                .map(category -> modelMapperService.forResponse()
                        .map(category, GetAllCategoryResponse.class)).collect(Collectors.toList());

        return result;
    }

    @Override
    public CreatedCategoryResponse add(CreateCategoryRequest createCategoryRequest) {
        Category category = modelMapperService.forRequest().map(createCategoryRequest, Category.class);

        Category createdCategory = categoryRepository.save(category);

        CreatedCategoryResponse createdCategoryResponse = modelMapperService.forResponse().map(createdCategory, CreatedCategoryResponse.class);

        return createdCategoryResponse;
    }
}
