package com.okuma.dostu.backend.controllers;

import com.okuma.dostu.backend.business.abstracts.CategoryService;
import com.okuma.dostu.backend.business.dtos.requests.categories.CreateCategoryRequest;
import com.okuma.dostu.backend.business.dtos.responses.categories.CreatedCategoryResponse;
import com.okuma.dostu.backend.business.dtos.responses.categories.GetAllCategoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoriesController {
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<GetAllCategoryResponse>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @PostMapping
    public CreatedCategoryResponse add(@RequestBody CreateCategoryRequest createCategoryRequest) {
        return categoryService.add(createCategoryRequest);
    }
}
