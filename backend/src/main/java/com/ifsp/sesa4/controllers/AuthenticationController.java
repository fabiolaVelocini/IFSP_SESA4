package com.ifsp.sesa4.controllers;

import com.ifsp.sesa4.DTOs.LoginDTO;
import com.ifsp.sesa4.DTOs.UserInfoDTO;
import com.ifsp.sesa4.conversors.EntityToDTO;
import com.ifsp.sesa4.entities.User;
import com.ifsp.sesa4.security.AuthenticationService;
import com.ifsp.sesa4.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final ConversionService conversionService;

    private final EntityToDTO entityToDTO;

    private final UserService userService;

    public AuthenticationController(
            UserService userService,
            ConversionService conversionService,
            EntityToDTO entityToDTO,
            AuthenticationService authenticationService
    ) {
        this.userService = userService;
        this.conversionService = conversionService;
        this.entityToDTO = entityToDTO;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public @ResponseBody String doLogin(@RequestBody LoginDTO loginDTO) {
        Optional<String> localBearerToken = this.authenticationService.generateToken(loginDTO);
        return localBearerToken.orElseThrow(() -> new ResponseStatusException(UNAUTHORIZED));
    }


    @PutMapping("/first-access")
    public ResponseEntity<?> firstAccess(@RequestBody LoginDTO firstAccess){
        this.authenticationService.doFirstAccess(firstAccess);
        return new ResponseEntity<>(OK);
    }

}

