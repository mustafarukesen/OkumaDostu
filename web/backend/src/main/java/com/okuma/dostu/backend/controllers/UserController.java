package com.okuma.dostu.backend.controllers;

import com.okuma.dostu.backend.business.abstracts.UserService;
import com.okuma.dostu.backend.core.security.user.ChangePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest, Principal connectedUser) {
        userService.changePassword(changePasswordRequest, connectedUser);
        return ResponseEntity.ok().build();
    }
}
