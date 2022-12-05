package com.ifsp.sesa4.controllers;

import com.ifsp.sesa4.DTOs.CreateUserDTO;
import com.ifsp.sesa4.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createApplicationUser(@RequestBody CreateUserDTO createUserDTO){
        this.userService.createUser(createUserDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
