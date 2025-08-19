package com.laxios.redirect.controller;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
@Data
public class RedirectController {

    @GetMapping("/")
    public String hello() {
        System.out.println("jello");
        return "jello";
    }
}
