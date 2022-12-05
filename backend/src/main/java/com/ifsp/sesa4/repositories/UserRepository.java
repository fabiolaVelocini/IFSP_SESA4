package com.ifsp.sesa4.repositories;

import com.ifsp.sesa4.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPasswordToken(String email, String passwordToken);

    Optional<User> findByUsername(String username);

}
