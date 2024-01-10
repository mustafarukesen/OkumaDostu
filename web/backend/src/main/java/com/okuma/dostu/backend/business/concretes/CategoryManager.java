package com.okuma.dostu.backend.business.concretes;

import com.okuma.dostu.backend.business.abstracts.CategoryService;
import com.okuma.dostu.backend.business.dtos.requests.categories.CreateCategoryRequest;
import com.okuma.dostu.backend.business.dtos.requests.categories.UpdateCategoryRequest;
import com.okuma.dostu.backend.business.dtos.responses.categories.CreatedCategoryResponse;
import com.okuma.dostu.backend.business.dtos.responses.categories.DeletedCategoryResponse;
import com.okuma.dostu.backend.business.dtos.responses.categories.GetAllCategoryResponse;
import com.okuma.dostu.backend.business.dtos.responses.categories.UpdatedCategoryResponse;
import com.okuma.dostu.backend.business.rules.CategoryBusinessRules;
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

    private CategoryRepository categoryRepository;
    private ModelMapperService modelMapperService;
    private CategoryBusinessRules categoryBusinessRules;

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
        categoryBusinessRules.checkIfCategoryNameExists(createCategoryRequest.getName());

        Category category = modelMapperService.forRequest().map(createCategoryRequest, Category.class);

        Category createdCategory = categoryRepository.save(category);

        CreatedCategoryResponse createdCategoryResponse = modelMapperService.forResponse().map(createdCategory, CreatedCategoryResponse.class);

        return createdCategoryResponse;
    }

    @Override
    public UpdatedCategoryResponse update(UpdateCategoryRequest updateCategoryRequest) {
        Category category = modelMapperService.forRequest().map(updateCategoryRequest, Category.class);

        Category updatedCategory = categoryRepository.save(category);

        UpdatedCategoryResponse updatedCategoryResponse = modelMapperService.forResponse().map(updatedCategory, UpdatedCategoryResponse.class);

        return updatedCategoryResponse;
    }

    @Override
    public DeletedCategoryResponse delete(int id) {
        Category categoryToDelete = new Category();
        categoryToDelete.setId(id);

        categoryRepository.delete(categoryToDelete);

        DeletedCategoryResponse deletedCategoryResponse = new DeletedCategoryResponse(id);

        return deletedCategoryResponse;
    }
}
