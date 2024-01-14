package com.okuma.dostu.backend.business.rules;

import com.okuma.dostu.backend.business.messages.business.AuthorBusinessMessages;
import com.okuma.dostu.backend.core.utilities.exceptions.types.BusinessException;
import com.okuma.dostu.backend.dataAccess.abstracts.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorBusinessRules {
    private AuthorRepository authorRepository;

    public void checkIfAuthorNameExists(String name) {
        if (authorRepository.existsByName(name))
            throw new BusinessException(AuthorBusinessMessages.authorNameAlreadyExists);
    }
}
