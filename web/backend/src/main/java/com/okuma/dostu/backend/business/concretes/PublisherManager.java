package com.okuma.dostu.backend.business.concretes;

import com.okuma.dostu.backend.business.abstracts.PublisherService;
import com.okuma.dostu.backend.business.dtos.requests.publishers.CreatePublisherRequest;
import com.okuma.dostu.backend.business.dtos.responses.publishers.CreatedPublisherResponse;
import com.okuma.dostu.backend.business.dtos.responses.publishers.GetAllPublisherResponse;
import com.okuma.dostu.backend.core.utilities.mappers.ModelMapperService;
import com.okuma.dostu.backend.dataAccess.abstracts.PublisherRepository;
import com.okuma.dostu.backend.entities.concretes.Publisher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PublisherManager implements PublisherService {
    PublisherRepository publisherRepository;
    ModelMapperService modelMapperService;


    @Override
    public List<GetAllPublisherResponse> getAll() {
        List<Publisher> publishers = publisherRepository.findAll();

        List<GetAllPublisherResponse> result = publishers.stream()
                .map(publisher -> modelMapperService.forResponse()
                        .map(publisher, GetAllPublisherResponse.class)).collect(Collectors.toList());

        return result;
    }

    @Override
    public CreatedPublisherResponse add(CreatePublisherRequest createPublisherRequest) {
        Publisher publisher = modelMapperService.forRequest().map(createPublisherRequest, Publisher.class);

        Publisher createdPublisher = publisherRepository.save(publisher);

        CreatedPublisherResponse createdPublisherResponse = modelMapperService.forResponse().map(createdPublisher, CreatedPublisherResponse.class);

        return createdPublisherResponse;
    }
}
