package com.ifsp.sesa4.entities;

import com.ifsp.sesa4.DTOs.CreateUserDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "password_token")
    private String passwordToken;

    public User(CreateUserDTO createUserDTO) {
        this.name = createUserDTO.getName();
        this.email = createUserDTO.getEmail();
        this.username = createUserDTO.getUsername();
    }
}
