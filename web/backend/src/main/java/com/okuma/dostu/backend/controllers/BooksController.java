package com.okuma.dostu.backend.controllers;

import com.okuma.dostu.backend.business.abstracts.BookService;
import com.okuma.dostu.backend.business.dtos.requests.books.CreateBookRequest;
import com.okuma.dostu.backend.business.dtos.responses.books.CreatedBookResponse;
import com.okuma.dostu.backend.business.dtos.responses.books.GetAllBookResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BooksController {
    BookService bookService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GetAllBookResponse>> getAll() {
        return ResponseEntity.ok(this.bookService.getAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedBookResponse add(@Valid @RequestBody CreateBookRequest createBookRequest) {
        return bookService.add(createBookRequest);
    }
}
