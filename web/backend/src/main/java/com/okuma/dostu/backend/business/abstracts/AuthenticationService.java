package com.okuma.dostu.backend.business.abstracts;

import com.okuma.dostu.backend.business.dtos.requests.auth.LoginRequest;
import com.okuma.dostu.backend.business.dtos.requests.auth.RegisterRequest;
import com.okuma.dostu.backend.business.dtos.responses.auth.LoginResponse;
import jakarta.mail.MessagingException;

public interface AuthenticationService {
    void register(RegisterRequest registerRequest) throws MessagingException;

    LoginResponse login(LoginRequest loginRequest);

    void activateAccount(String token) throws MessagingException;
}
