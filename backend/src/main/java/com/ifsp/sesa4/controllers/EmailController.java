package com.ifsp.sesa4.controllers;

import com.ifsp.sesa4.DTOs.CreateUserDTO;
import com.ifsp.sesa4.DTOs.EmailRequestDTO;
import com.ifsp.sesa4.services.EmailService;
import com.ifsp.sesa4.services.UserService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
@AllArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<?> sendPersonalizedEmail(@RequestBody EmailRequestDTO emailRequestDTO) throws MessagingException {
        this.emailService.sendPersonalizedEmail(emailRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
