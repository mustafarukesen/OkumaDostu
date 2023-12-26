package com.okuma.dostu.backend.business.rules;

import com.okuma.dostu.backend.core.utilities.exceptions.BusinessException;
import com.okuma.dostu.backend.dataAccess.abstracts.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookBusinessRules {
    private BookRepository bookRepository;

    public void checkIfBookNameExists(String name)
    {
        if (this.bookRepository.existsByTitle(name))
        {
            throw new BusinessException("Book name already exists!");
        }
    }
}
