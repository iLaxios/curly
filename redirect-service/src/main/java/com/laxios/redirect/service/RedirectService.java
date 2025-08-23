package com.laxios.redirect.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laxios.redirect.entity.UrlMapping;
import com.laxios.commons.events.UrlClickedEvent;
import com.laxios.redirect.repository.UrlMappingRepository;
import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@Data
public class RedirectService {

    private final RedisTemplate<String, UrlMapping> redisTemplate;
    private final UrlMappingRepository urlMappingRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public UrlMapping getOriginalUrl(String shortCode) {
        UrlMapping mapping = redisTemplate.opsForValue().get(shortCode);
        if (mapping != null) return mapping;

        Optional<UrlMapping> dbMapping = urlMappingRepository.findByShortCode(shortCode);
        dbMapping.ifPresent(m -> redisTemplate.opsForValue().set(shortCode, m));
        return dbMapping.orElse(null);
    }

    public void sendClickEvent(String shortCode) {
        try {
            UrlClickedEvent event = new UrlClickedEvent(shortCode, LocalDateTime.now());
            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("url-clicks", message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize event", e);
        }
    }
}