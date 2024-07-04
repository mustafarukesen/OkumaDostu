package com.okuma.dostu.backend.controllers;

import com.okuma.dostu.backend.business.abstracts.RecommendationService;
import com.okuma.dostu.backend.business.abstracts.UserService;
import com.okuma.dostu.backend.business.dtos.requests.auth.ChangePasswordRequest;
import com.okuma.dostu.backend.business.dtos.requests.auth.UpdateRequest;
import com.okuma.dostu.backend.business.dtos.responses.users.GetAllRecommendationResponse;
import com.okuma.dostu.backend.business.dtos.responses.users.UserUpdateResponse;
import com.okuma.dostu.backend.core.security.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("#id == principal.id")
    public ResponseEntity<UserUpdateResponse> update(
            @PathVariable Integer id,
            @RequestBody UpdateRequest updateRequest
    ) {
        return ResponseEntity.ok(userService.updateUser(id, updateRequest));
    }

    @GetMapping("/recommendations")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<GetAllRecommendationResponse>> getUserRecommendations(Principal connectedUser, Pageable pageable) {
        return ResponseEntity.ok(recommendationService.getAll(connectedUser, pageable));
    }
}
