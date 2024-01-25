package com.okuma.dostu.backend.business.dtos.responses.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;

    private String firstName;
    private String lastName;
    private String email;

    @JsonProperty("refresh_token")
    private String refreshToken;
}
