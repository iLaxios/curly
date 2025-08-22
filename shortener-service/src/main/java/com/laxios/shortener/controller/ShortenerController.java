package com.laxios.shortener.controller;

import com.laxios.shortener.dto.ShortenRequest;
import com.laxios.shortener.service.ShortenerService;
import lombok.Data;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/api/shorten")
public class ShortenerController {

    final ShortenerService shortenerService;
    @PostMapping
    public String shorten(
            @RequestBody ShortenRequest shortenRequest,
            @RequestHeader(value = "Authorization", required = false) String authHeader
    ) {
        String inputUrl = shortenRequest.getUrl();

        // Extract JWT if present
        String jwt = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            System.out.println(jwt);
        }

        String shortenUrl = shortenerService.shortenURL(inputUrl, jwt);
        return shortenUrl;
    }
}
