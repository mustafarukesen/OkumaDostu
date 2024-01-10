package com.okuma.dostu.backend.business.rules;

import com.okuma.dostu.backend.business.messages.CategoryBusinessMessages;
import com.okuma.dostu.backend.core.utilities.exceptions.types.BusinessException;
import com.okuma.dostu.backend.dataAccess.abstracts.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryBusinessRules {
    private CategoryRepository categoryRepository;

    public void checkIfCategoryNameExists(String name) {
        if (categoryRepository.existsByName(name))
            throw new BusinessException(CategoryBusinessMessages.categoryNameAlreadyExists);
    }
}
