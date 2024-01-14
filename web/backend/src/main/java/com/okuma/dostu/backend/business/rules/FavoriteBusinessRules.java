package com.okuma.dostu.backend.business.rules;

import com.okuma.dostu.backend.business.messages.business.FavoriteBusinessMessages;
import com.okuma.dostu.backend.core.security.user.User;
import com.okuma.dostu.backend.core.utilities.exceptions.types.BusinessException;
import com.okuma.dostu.backend.dataAccess.abstracts.FavoriteRepository;
import com.okuma.dostu.backend.entities.concretes.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FavoriteBusinessRules {
    private FavoriteRepository favoriteRepository;

    public void checkExistByUserAndBook(User user, Book book) {
        if (favoriteRepository.existsByUserAndBook(user, book))
            throw new BusinessException(FavoriteBusinessMessages.checkExistByUserAndBook);
    }
}
