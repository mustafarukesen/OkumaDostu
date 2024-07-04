package com.okuma.dostu.backend.business.dtos.responses.users;

import com.okuma.dostu.backend.core.security.user.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateResponse {
    private User user;
}
