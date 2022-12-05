package com.ifsp.sesa4.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticatedUserJwt {

    String token;

    String refreshToken;

    String username;

    Map<String, String> userDetail;

    public AuthenticatedUserJwt(String token, String refreshToken, String username) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.username = username;
    }

}
