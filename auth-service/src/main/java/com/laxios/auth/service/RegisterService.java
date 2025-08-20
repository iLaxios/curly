package com.laxios.auth.service;

import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    public void registerUser(String user, String password) {
        System.out.println("user and password recieve as " + user + " " + password);
    }
}
