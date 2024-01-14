package com.okuma.dostu.backend.business.rules;

import com.okuma.dostu.backend.business.messages.business.PublisherBusinessMessages;
import com.okuma.dostu.backend.core.utilities.exceptions.types.BusinessException;
import com.okuma.dostu.backend.dataAccess.abstracts.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PublisherBusinessRules {
    private PublisherRepository publisherRepository;

    public void checkIfPublisherNameExists(String name) {
        if (publisherRepository.existsByName(name))
            throw new BusinessException(PublisherBusinessMessages.publisherNameAlreadyExists);
    }
}
