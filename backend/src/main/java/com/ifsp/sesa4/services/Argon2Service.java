package com.ifsp.sesa4.services;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Argon2Service {

    @Bean
    public PasswordEncoder encoder() {
        return new Argon2PasswordEncoder(128, 128, 16, 2048, 256);
    }

}
