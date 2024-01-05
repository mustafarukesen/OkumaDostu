package com.okuma.dostu.backend.controllers;

import com.okuma.dostu.backend.business.abstracts.CategoryService;
import com.okuma.dostu.backend.business.dtos.requests.categories.CreateCategoryRequest;
import com.okuma.dostu.backend.business.dtos.requests.categories.UpdateCategoryRequest;
import com.okuma.dostu.backend.business.dtos.responses.categories.CreatedCategoryResponse;
import com.okuma.dostu.backend.business.dtos.responses.categories.DeletedCategoryResponse;
import com.okuma.dostu.backend.business.dtos.responses.categories.GetAllCategoryResponse;
import com.okuma.dostu.backend.business.dtos.responses.categories.UpdatedCategoryResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
public class CategoriesController {
    CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GetAllCategoryResponse>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('admin:create')")
    public CreatedCategoryResponse add(@RequestBody @Valid CreateCategoryRequest createCategoryRequest) {
        return categoryService.add(createCategoryRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin:update')")
    public UpdatedCategoryResponse update(@RequestBody @Valid UpdateCategoryRequest updateCategoryRequest) {
        return categoryService.update(updateCategoryRequest);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin:delete')")
    public DeletedCategoryResponse delete(@RequestBody int id) {
        return categoryService.delete(id);
    }
}
