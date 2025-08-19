package com.laxios.shortener.controller;

import com.laxios.shortener.dto.ShortenRequest;
import com.laxios.shortener.service.ShortenerService;
import lombok.Data;
import org.apache.commons.validator.routines.UrlValidator;
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
        String inputUrl = shortenRequest.getUrl();

        UrlValidator validator = new UrlValidator(
                new String[] {"http","https"},
                UrlValidator.ALLOW_LOCAL_URLS // remember to remove this;
        );
        if (!validator.isValid(inputUrl)) {
            throw new IllegalArgumentException("Invalid URL format");
        }

        return "Shorten endpoint hit!";
    }
}
