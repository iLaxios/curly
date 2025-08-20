package com.laxios.auth.controller;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
public class AuthController {
    @PostMapping
    public String handleRegister() {
        return "hello World";
    }
}
