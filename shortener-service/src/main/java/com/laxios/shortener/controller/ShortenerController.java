package com.laxios.shortener.controller;

import com.laxios.shortener.dto.ShortenRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shorten")
public class ShortenerController {

    @PostMapping
    public String shorten(@RequestBody ShortenRequest shortenRequest) {
        System.out.println("Shorten called " + shortenRequest.getUrl());
        return "Shorten endpoint hit!";
    }
}
