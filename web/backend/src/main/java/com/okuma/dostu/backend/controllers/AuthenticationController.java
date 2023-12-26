package com.okuma.dostu.backend.controllers;

import com.okuma.dostu.backend.business.abstracts.AuthenticationService;
import com.okuma.dostu.backend.business.dtos.requests.auth.LoginRequest;
import com.okuma.dostu.backend.business.dtos.requests.auth.RegisterRequest;
import com.okuma.dostu.backend.business.dtos.responses.auth.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest
    ) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginRequest loginRequest
    ) {
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }
}
