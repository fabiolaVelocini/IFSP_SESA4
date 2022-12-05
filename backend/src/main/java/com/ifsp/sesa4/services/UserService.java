package com.ifsp.sesa4.services;

import com.ifsp.sesa4.DTOs.CreateUserDTO;
import com.ifsp.sesa4.entities.User;
import com.ifsp.sesa4.events.UserCreated;
import com.ifsp.sesa4.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private ApplicationEventPublisher eventPublisher;

    @Transactional
    public void createUser(CreateUserDTO createUserDTO) {

        this.userRepository.findByEmail(createUserDTO.email).ifPresent((user) -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"email");
        });

        this.userRepository.findByEmail(createUserDTO.username).ifPresent((user) -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"username");
        });

        User user = new User(createUserDTO);

        user.setPasswordToken(UUID.randomUUID().toString());

        User savedUser = this.userRepository.save(user);
        this.eventPublisher.publishEvent(new UserCreated(this,savedUser));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
