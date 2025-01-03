package com.kcare.kcare.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    Optional<Token> findByToken(String otp);

    // Optional<Token> findByUserEmailAndToken(String email, String token);

    // Optional<Token> findByUserContactNumberAndToken(String phoneNumber, String
    // token);

    void deleteAllByExpiresAtBefore(LocalDateTime now);

    List<Token> findAllByUserId(Integer userId);

    // Optional<Token> findByUserEmailAndToken(String email);

}