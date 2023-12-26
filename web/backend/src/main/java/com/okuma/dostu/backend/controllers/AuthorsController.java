package com.okuma.dostu.backend.controllers;

import com.okuma.dostu.backend.business.abstracts.AuthorService;
import com.okuma.dostu.backend.business.dtos.requests.authors.CreateAuthorRequest;
import com.okuma.dostu.backend.business.dtos.responses.authors.CreatedAuthorResponse;
import com.okuma.dostu.backend.business.dtos.responses.authors.GetAllAuthorResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@AllArgsConstructor
public class AuthorsController {
    AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<GetAllAuthorResponse>> getAll() {
        return ResponseEntity.ok(authorService.getAll());
    }

    @PostMapping
    public CreatedAuthorResponse add(@RequestBody CreateAuthorRequest createAuthorRequest) {
        return authorService.add(createAuthorRequest);
    }

}
