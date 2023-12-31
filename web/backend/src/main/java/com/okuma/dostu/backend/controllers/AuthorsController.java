package com.okuma.dostu.backend.controllers;

import com.okuma.dostu.backend.business.abstracts.AuthorService;
import com.okuma.dostu.backend.business.dtos.requests.authors.CreateAuthorRequest;
import com.okuma.dostu.backend.business.dtos.responses.authors.CreatedAuthorResponse;
import com.okuma.dostu.backend.business.dtos.responses.authors.GetAllAuthorResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@AllArgsConstructor
public class AuthorsController {
    AuthorService authorService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GetAllAuthorResponse>> getAll() {
        return ResponseEntity.ok(authorService.getAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAuthorResponse add(@Valid @RequestBody CreateAuthorRequest createAuthorRequest) {
        return authorService.add(createAuthorRequest);
    }

}
