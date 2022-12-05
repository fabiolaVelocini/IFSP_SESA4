package com.ifsp.sesa4.security;

import com.ifsp.sesa4.DTOs.LoginDTO;
import com.ifsp.sesa4.entities.User;
import com.ifsp.sesa4.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final String jwtSecret;

    public AuthenticationService(
            @Value("${jwt.secret}") String jwtSecret
    ){
        this.jwtSecret = jwtSecret;
    }

    @Autowired
    private JwtTokenValidator jwtTokenValidator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public Optional<String> generateToken(LoginDTO loginDTO){
        return this.userRepository
                .findByEmail(loginDTO.email)
                .map(user -> {
                    //boolean passwordMatches = this.passwordEncoder.matches(loginDTO.password, user.getPassword());
                    if(loginDTO.password.equals(user.getPassword())){
                        return JwtTokenGenerator
                                .generateToken(
                                        user.getName(),
                                        user.getId().toString(),
                                        jwtSecret
                                );
                    } else {
                        return null;
                    }
                });
    }

    public void doFirstAccess(LoginDTO firstAccess) {

        User user = this.userRepository
                .findByEmail(firstAccess.email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        //String encodedPassword = passwordEncoder.encode(firstAccess.password);
        user.setPassword(firstAccess.password);
        user.setPasswordToken(null);

        this.userRepository.save(user);
    }

    public AuthenticationInfo authenticateUser(String bearerToken) throws AuthenticationException {
        Optional<AuthenticationInfo> authenticationInfo = jwtTokenValidator.parseToken(bearerToken);
        if(authenticationInfo.isPresent()){
            return authenticationInfo.get();
        }
        return null;
    }

    public void verifyToken(String email, String token) {
        this.userRepository
                .findByEmailAndPasswordToken(email, token)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
