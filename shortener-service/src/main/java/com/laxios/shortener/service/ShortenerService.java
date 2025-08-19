package com.laxios.shortener.service;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ShortenerService {

    // In-memory store for now (later replace with Postgres/Redis)
    private final Map<String, String> urlStore = new HashMap<>();

    private static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_CODE_LENGTH = 6;

    public String shortenURL(String inputUrl) {

        // Validate url
        UrlValidator validator = new UrlValidator(
            new String[]{"http", "https"},
            UrlValidator.ALLOW_LOCAL_URLS // remember to remove this
        );

        if (!validator.isValid(inputUrl)) {
            throw new IllegalArgumentException("Invalid URL format");
        }

        // If URL already shortened, return existing code
        for (Map.Entry<String, String> entry : urlStore.entrySet()) {
            if (entry.getValue().equals(inputUrl)) {
                return entry.getKey();
            }
        }

        // Generate short code
        String shortCode = generateShortCode();

        // Store mapping
        urlStore.put(shortCode, inputUrl);

        return "http://cur.ly/" + shortCode;
    }

    public String getOriginalURL(String shortCode) {
        return urlStore.get(shortCode);
    }

    private String generateShortCode() {
        StringBuilder sb = new StringBuilder(SHORT_CODE_LENGTH);
        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            int idx = ThreadLocalRandom.current().nextInt(ALLOWED_CHARS.length());
            sb.append(ALLOWED_CHARS.charAt(idx));
        }
        return sb.toString();
    }
}
