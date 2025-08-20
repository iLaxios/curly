package com.laxios.auth.service;

import com.laxios.auth.dto.LoginRequest;
import com.laxios.auth.model.User;
import com.laxios.auth.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Data
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret:LifeIsNotAllSunshineAndRosesSoKeepCalmAnd69}")
    private String jwtSecret;

    @Value("${jwt.expiration:3600000}") // default 1h
    private long jwtExpirationMs;

    public User registerUser(String username, String password) {

        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already taken");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // hash password

        return userRepository.save(user);
    }

    public String loginUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println(user.getPassword() + " " + password);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes())
                .compact();
    }
}
