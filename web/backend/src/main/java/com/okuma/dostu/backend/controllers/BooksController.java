package com.okuma.dostu.backend.controllers;

import com.okuma.dostu.backend.business.abstracts.BookService;
import com.okuma.dostu.backend.business.dtos.requests.books.CreateBookRequest;
import com.okuma.dostu.backend.business.dtos.requests.books.UpdateBookRequest;
import com.okuma.dostu.backend.business.dtos.responses.books.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BooksController {
    private BookService bookService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GetAllBookResponse>> getAll() {
        return ResponseEntity.ok(this.bookService.getAll());
    }

    @GetMapping("/pagination")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<GetAllBookResponse>> getAllWithPagination(Pageable pageable) {
        return ResponseEntity.ok(this.bookService.getAllWithPagination(pageable));
    }

    @GetMapping("/title")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<GetByTitleBookResponse>> getByTitle(@RequestParam String title, Pageable pageable) {
        return ResponseEntity.ok(bookService.getByTitle(title, pageable));
    }

    @GetMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetByIdBookResponse> getById(@RequestParam int id) {
        return ResponseEntity.ok(bookService.getById(id));
    }

    @GetMapping("/author")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<GetByAuthorNameBookResponse>> getByBooksWithAuthorName(@RequestParam String authorName, Pageable pageable) {
        return ResponseEntity.ok(bookService.getByAuthorName(authorName, pageable));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('admin:create')")
    public CreatedBookResponse add(@RequestBody @Valid CreateBookRequest createBookRequest) {
        return bookService.add(createBookRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin:update')")
    public UpdatedBookResponse update(@RequestBody @Valid UpdateBookRequest updateBookRequest) {
        return bookService.update(updateBookRequest);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin:delete')")
    public DeletedBookResponse delete(@RequestBody int id) {
        return bookService.delete(id);
    }
}
