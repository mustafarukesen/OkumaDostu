package com.okuma.dostu.backend.business.dtos.responses.auth;

import com.okuma.dostu.backend.core.security.user.User;
import com.okuma.dostu.backend.entities.concretes.Favorite;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginResponse {

    private String token;

    private User user;

    private List<Favorite> favorites;
}
