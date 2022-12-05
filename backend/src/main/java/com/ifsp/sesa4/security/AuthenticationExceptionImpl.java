package com.ifsp.sesa4.security;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationExceptionImpl extends AuthenticationException {

    public AuthenticationExceptionImpl(String msg) {
        super(msg);
    }

}
