package com.okuma.dostu.backend.controllers;

import com.okuma.dostu.backend.business.abstracts.PublisherService;
import com.okuma.dostu.backend.business.dtos.requests.publishers.CreatePublisherRequest;
import com.okuma.dostu.backend.business.dtos.responses.publishers.CreatedPublisherResponse;
import com.okuma.dostu.backend.business.dtos.responses.publishers.GetAllPublisherResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publishers")
@AllArgsConstructor
public class PublishersController {
    PublisherService publisherService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GetAllPublisherResponse>> getAll() {
        return ResponseEntity.ok(publisherService.getAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedPublisherResponse add(@Valid @RequestBody CreatePublisherRequest createPublisherRequest) {
        return publisherService.add(createPublisherRequest);
    }
}
