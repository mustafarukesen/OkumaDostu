package com.okuma.dostu.backend.business.rules;

import com.okuma.dostu.backend.business.dtos.requests.auth.ChangePasswordRequest;
import com.okuma.dostu.backend.business.messages.business.UserBusinessMessages;
import com.okuma.dostu.backend.core.security.user.User;
import com.okuma.dostu.backend.core.utilities.exceptions.types.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBusinessRules {
    private PasswordEncoder passwordEncoder;

    public void checkUserPasswordExists(ChangePasswordRequest changePasswordRequest, User user) {
        if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.getPassword())) {
            throw new BusinessException(UserBusinessMessages.userPasswordNotExists);
        }
    }

    public void checkUserConfirmPasword(ChangePasswordRequest changePasswordRequest) {
        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmationPassword()))
            throw new BusinessException(UserBusinessMessages.userConfirmPasswordIsTrue);
    }
}
