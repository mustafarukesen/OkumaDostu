package com.okuma.dostu.backend.business.concretes;

import com.okuma.dostu.backend.business.abstracts.UserService;
import com.okuma.dostu.backend.business.dtos.requests.auth.ChangePasswordRequest;
import com.okuma.dostu.backend.business.dtos.requests.auth.UpdateRequest;
import com.okuma.dostu.backend.business.dtos.responses.users.UserUpdateResponse;
import com.okuma.dostu.backend.business.rules.UserBusinessRules;
import com.okuma.dostu.backend.core.file.FileService;
import com.okuma.dostu.backend.core.security.user.User;
import com.okuma.dostu.backend.dataAccess.abstracts.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserBusinessRules userBusinessRules;
    private final FileService fileService;

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        userBusinessRules.checkUserPasswordExists(changePasswordRequest, user);
        userBusinessRules.checkUserConfirmPasword(changePasswordRequest);

        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));

        userRepository.save(user);
    }

    @Override
    public User getUser(Integer id) {
        return userRepository.findById(id).orElseThrow(null);
    }

    @Override
    public UserUpdateResponse updateUser(Integer id, UpdateRequest updateRequest) {
        var user = getUser(id);

        user.setFirstName(updateRequest.getFirstName());
        user.setLastName(updateRequest.getLastName());
        user.setDateOfBirth(updateRequest.getDateOfBirth());

        if (Objects.nonNull(updateRequest.getImage())) {
            String fileName = fileService.saveBase64StringAsFile(updateRequest.getImage());
            fileService.deleteProfileImage(user.getImage());
            user.setImage(fileName);
        }

        userRepository.save(user);

        return UserUpdateResponse.builder()
                .user(user)
                .build();
    }
}
