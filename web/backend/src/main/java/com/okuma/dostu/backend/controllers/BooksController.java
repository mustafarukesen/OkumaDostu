package com.okuma.dostu.backend.controllers;

import com.okuma.dostu.backend.business.abstracts.BookService;
import com.okuma.dostu.backend.business.dtos.requests.books.CreateBookRequest;
import com.okuma.dostu.backend.business.dtos.requests.books.UpdateBookRequest;
import com.okuma.dostu.backend.business.dtos.responses.books.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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

    @GetMapping("/title")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GetByTitleBookResponse>> getByTitle(@RequestParam String title) {
        return ResponseEntity.ok(bookService.getByTitle(title));
    }

    @GetMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetByIdBookResponse> getById(@RequestParam int id) {
        return ResponseEntity.ok(bookService.getById(id));
    }

    @GetMapping("/author")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GetByBooksWithAuthorNameBookResponse>> getByBooksWithAuthorName(String authorName) {
        return ResponseEntity.ok(bookService.getByBooksWithAuthorName(authorName));
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
