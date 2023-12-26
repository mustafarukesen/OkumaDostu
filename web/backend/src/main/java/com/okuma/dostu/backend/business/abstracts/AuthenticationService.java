package com.okuma.dostu.backend.business.abstracts;

import com.okuma.dostu.backend.business.dtos.requests.auth.LoginRequest;
import com.okuma.dostu.backend.business.dtos.requests.auth.RegisterRequest;
import com.okuma.dostu.backend.business.dtos.responses.auth.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest registerRequest);

    AuthenticationResponse login(LoginRequest loginRequest);
}
