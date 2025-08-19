package com.laxios.redirect.controller;

import com.laxios.redirect.entity.UrlMapping;
import com.laxios.redirect.service.RedirectService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Service
@RestController
@Data
public class RedirectController {


    private final RedirectService redirectService;


    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {

        if (!shortCode.matches("[a-zA-Z0-9]{6}")) {
            throw new IllegalArgumentException("Invalid short code format");
        }

        UrlMapping urlMapping = redirectService.getOriginalUrl(shortCode);

        if (urlMapping == null) return ResponseEntity.notFound().build();

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(urlMapping.getOriginalUrl()))
                .build();
    }
}
