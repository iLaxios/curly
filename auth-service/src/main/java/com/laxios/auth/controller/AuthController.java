package com.laxios.auth.controller;

import com.laxios.auth.dto.LoginRequest;
import com.laxios.auth.dto.RegisterRequest;
import com.laxios.auth.service.UserService;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public String handleRegister(@RequestBody RegisterRequest registerRequest) {
        userService.registerUser(registerRequest.getUsername(), registerRequest.getPassword());
        return registerRequest.getUsername();
    }

    @PostMapping("/login")
    public String handleLogin(@RequestBody LoginRequest loginRequest) {
        userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        return userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
    }
}
