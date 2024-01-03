package com.okuma.dostu.backend.controllers;

import com.okuma.dostu.backend.business.abstracts.BookService;
import com.okuma.dostu.backend.business.dtos.requests.books.CreateBookRequest;
import com.okuma.dostu.backend.business.dtos.requests.books.UpdateBookRequest;
import com.okuma.dostu.backend.business.dtos.responses.books.CreatedBookResponse;
import com.okuma.dostu.backend.business.dtos.responses.books.DeletedBookResponse;
import com.okuma.dostu.backend.business.dtos.responses.books.GetAllBookResponse;
import com.okuma.dostu.backend.business.dtos.responses.books.UpdatedBookResponse;
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
    public CreatedBookResponse add(@RequestBody @Valid CreateBookRequest createBookRequest) {
        return bookService.add(createBookRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedBookResponse update(@RequestBody @Valid UpdateBookRequest updateBookRequest) {
        return bookService.update(updateBookRequest);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public DeletedBookResponse delete(@RequestBody int id) {
        return bookService.delete(id);
    }
}
