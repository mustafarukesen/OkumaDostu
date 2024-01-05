package com.okuma.dostu.backend.controllers;

import com.okuma.dostu.backend.business.abstracts.AuthorService;
import com.okuma.dostu.backend.business.abstracts.BookService;
import com.okuma.dostu.backend.business.abstracts.CategoryService;
import com.okuma.dostu.backend.business.abstracts.PublisherService;
import com.okuma.dostu.backend.business.dtos.responses.authors.GetAllAuthorResponse;
import com.okuma.dostu.backend.business.dtos.responses.books.GetAllBookResponse;
import com.okuma.dostu.backend.business.dtos.responses.categories.GetAllCategoryResponse;
import com.okuma.dostu.backend.business.dtos.responses.publishers.GetAllPublisherResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
public class AdminController {

    private BookService bookService;
    private AuthorService authorService;
    private CategoryService categoryService;
    private PublisherService publisherService;

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public String admin() {
        return "Yönetici Sayfası";
    }

    @GetMapping("/books")
    @PreAuthorize("hasAuthority('admin:read')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GetAllBookResponse>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping("/authors")
    @PreAuthorize("hasAuthority('admin:read')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GetAllAuthorResponse>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAll());
    }

    @GetMapping("/categories")
    @PreAuthorize("hasAuthority('admin:read')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GetAllCategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/publishers")
    @PreAuthorize("hasAuthority('admin:read')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GetAllPublisherResponse>> getAllPublishers() {
        return ResponseEntity.ok(publisherService.getAll());
    }
}
