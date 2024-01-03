package com.okuma.dostu.backend.controllers;

import com.okuma.dostu.backend.business.abstracts.PublisherService;
import com.okuma.dostu.backend.business.dtos.requests.publishers.CreatePublisherRequest;
import com.okuma.dostu.backend.business.dtos.requests.publishers.UpdatePublisherRequest;
import com.okuma.dostu.backend.business.dtos.responses.publishers.CreatedPublisherResponse;
import com.okuma.dostu.backend.business.dtos.responses.publishers.DeletedPublisherResponse;
import com.okuma.dostu.backend.business.dtos.responses.publishers.GetAllPublisherResponse;
import com.okuma.dostu.backend.business.dtos.responses.publishers.UpdatedPublisherResponse;
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
    public CreatedPublisherResponse add(@RequestBody @Valid CreatePublisherRequest createPublisherRequest) {
        return publisherService.add(createPublisherRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedPublisherResponse update(@RequestBody @Valid UpdatePublisherRequest updatePublisherRequest) {
        return publisherService.update(updatePublisherRequest);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public DeletedPublisherResponse delete(@RequestBody int id) {
        return publisherService.delete(id);
    }
}
