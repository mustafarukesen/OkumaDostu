package com.okuma.dostu.backend.business.concretes;

import com.okuma.dostu.backend.business.abstracts.AuthenticationService;
import com.okuma.dostu.backend.business.dtos.requests.auth.LoginRequest;
import com.okuma.dostu.backend.business.dtos.requests.auth.RegisterRequest;
import com.okuma.dostu.backend.business.dtos.responses.auth.AuthenticationResponse;
import com.okuma.dostu.backend.core.security.jwt.JwtService;
import com.okuma.dostu.backend.core.security.user.Role;
import com.okuma.dostu.backend.core.security.user.User;
import com.okuma.dostu.backend.dataAccess.abstracts.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationManagers implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode((registerRequest.getPassword())))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        var user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
