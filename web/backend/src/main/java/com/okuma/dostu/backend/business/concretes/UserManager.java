package com.okuma.dostu.backend.business.concretes;

import com.okuma.dostu.backend.business.abstracts.UserService;
import com.okuma.dostu.backend.business.dtos.requests.auth.ChangePasswordRequest;
import com.okuma.dostu.backend.business.rules.UserBusinessRules;
import com.okuma.dostu.backend.core.security.user.User;
import com.okuma.dostu.backend.dataAccess.abstracts.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserBusinessRules userBusinessRules;

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        userBusinessRules.checkUserPasswordExists(changePasswordRequest, user);
        userBusinessRules.checkUserConfirmPasword(changePasswordRequest);

        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));

        userRepository.save(user);
    }
}
