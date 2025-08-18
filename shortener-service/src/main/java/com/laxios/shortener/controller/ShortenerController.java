package com.laxios.shortener.controller;

import com.laxios.shortener.dto.ShortenRequest;
import com.laxios.shortener.service.ShortenerService;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
@RequestMapping("/api/shorten")
public class ShortenerController {

    final ShortenerService shortenerService;
    @PostMapping
    public String shorten(@RequestBody ShortenRequest shortenRequest) {
        shortenerService.processURL();
        System.out.println("Shorten called " + shortenRequest.getUrl());
        return "Shorten endpoint hit!";
    }
}
