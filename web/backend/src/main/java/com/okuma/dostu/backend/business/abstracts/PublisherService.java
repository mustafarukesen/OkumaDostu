package com.okuma.dostu.backend.business.abstracts;

import com.okuma.dostu.backend.business.dtos.requests.publishers.CreatePublisherRequest;
import com.okuma.dostu.backend.business.dtos.responses.publishers.CreatedPublisherResponse;
import com.okuma.dostu.backend.business.dtos.responses.publishers.GetAllPublisherResponse;

import java.util.List;

public interface PublisherService {
    List<GetAllPublisherResponse> getAll();

    CreatedPublisherResponse add(CreatePublisherRequest createPublisherRequest);
}
