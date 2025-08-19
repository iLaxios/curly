package com.laxios.shortener.service;

import com.laxios.shortener.entity.UrlMapping;
import com.laxios.shortener.repository.UrlMappingRepository;
import lombok.Data;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Data
@Service
public class ShortenerService {

    private final UrlMappingRepository urlMappingRepository;
    private final RedisTemplate<String, Object> redisTemplate;


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

        // If URL already shortened, return existing code from DB if not in cache
        Optional<UrlMapping> existing = urlMappingRepository.findByOriginalUrl(inputUrl);
        if (existing.isPresent()) {
            return existing.get().getShortCode();
        }

        // Generate short code
        String shortCode = generateShortCode();

        UrlMapping mapping = UrlMapping.builder()
                .originalUrl(inputUrl)
                .shortCode(shortCode)
                .build();

        urlMappingRepository.save(mapping);

        // Save to Redis
        redisTemplate.opsForValue().set(shortCode, mapping);

        return "http://cur.ly/" + shortCode;
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
