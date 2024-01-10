package com.okuma.dostu.backend.controllers;

import com.okuma.dostu.backend.business.abstracts.RecommendationService;
import com.okuma.dostu.backend.business.abstracts.UserService;
import com.okuma.dostu.backend.business.dtos.requests.auth.ChangePasswordRequest;
import com.okuma.dostu.backend.business.dtos.responses.users.GetAllRecommendationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RecommendationService recommendationService;

    @PostMapping
    public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest, Principal connectedUser) {
        userService.changePassword(changePasswordRequest, connectedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/recommendations")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GetAllRecommendationResponse>> getUserRecommendations(Principal connectedUser) {
        return ResponseEntity.ok(recommendationService.getAll(connectedUser));
    }
}
