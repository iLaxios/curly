package com.laxios.shortener.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shorten")
public class ShortenerController {

    @PostMapping
    public String shorten() {
        System.out.println("Shorten called");
        return "Shorten endpoint hit!";
    }
}
