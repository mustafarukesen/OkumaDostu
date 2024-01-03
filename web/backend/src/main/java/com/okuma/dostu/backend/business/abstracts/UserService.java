package com.okuma.dostu.backend.business.abstracts;

import com.okuma.dostu.backend.core.security.user.ChangePasswordRequest;

import java.security.Principal;

public interface UserService {
    void changePassword(ChangePasswordRequest changePasswordRequest, Principal connectedUser);
}
