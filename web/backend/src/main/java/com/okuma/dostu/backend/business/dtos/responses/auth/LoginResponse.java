package com.okuma.dostu.backend.business.dtos.responses.auth;

import com.okuma.dostu.backend.core.security.user.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private String token;

    private User user;
}
