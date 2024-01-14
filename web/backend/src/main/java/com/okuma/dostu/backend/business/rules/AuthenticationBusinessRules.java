package com.okuma.dostu.backend.business.rules;

import com.okuma.dostu.backend.business.messages.business.AuthenticationBusinessMessages;
import com.okuma.dostu.backend.core.security.user.User;
import com.okuma.dostu.backend.core.utilities.exceptions.types.BusinessException;
import com.okuma.dostu.backend.dataAccess.abstracts.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationBusinessRules {
    private UserRepository userRepository;

    public void checkIfEmailAddressAlreadyExists(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent())
            throw new BusinessException(AuthenticationBusinessMessages.emailAddressAlreadyExists);
    }
}
