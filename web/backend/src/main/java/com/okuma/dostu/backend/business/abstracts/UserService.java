package com.okuma.dostu.backend.business.abstracts;

import com.okuma.dostu.backend.business.dtos.requests.auth.ChangePasswordRequest;
import com.okuma.dostu.backend.business.dtos.requests.auth.UpdateRequest;
import com.okuma.dostu.backend.business.dtos.responses.users.UserUpdateResponse;
import com.okuma.dostu.backend.core.security.user.User;

import java.security.Principal;

public interface UserService {
    void changePassword(ChangePasswordRequest changePasswordRequest, Principal connectedUser);

    User getUser(Integer id);

    UserUpdateResponse updateUser(Integer id, UpdateRequest updateRequest);
}
