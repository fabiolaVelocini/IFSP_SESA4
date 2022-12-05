package com.ifsp.sesa4.security;

import lombok.Data;

@Data
public class JwtRequest {

    private String email;

    private String password;

}
