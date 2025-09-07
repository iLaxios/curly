package com.laxios.redirect.service;

import com.laxios.redirect.entity.UrlMapping;
import com.laxios.commons.events.UrlClickedEvent;
import com.laxios.redirect.metrics.RedirectMetrics;
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
    private final KafkaTemplate<String, UrlClickedEvent> kafkaTemplate;
    private final RedirectMetrics metrics;

    public UrlMapping getOriginalUrl(String shortCode) {

        metrics.recordRequest();

        return metrics.recordLatency(() -> {
            try{

                UrlMapping mapping = redisTemplate.opsForValue().get(shortCode);
                if (mapping != null) {
                    metrics.recordCacheHit();
                    return mapping;
                }

                metrics.recordCacheMiss();

                Optional<UrlMapping> dbMapping = urlMappingRepository.findByShortCode(shortCode);
                if (dbMapping.isPresent()) {
                    redisTemplate.opsForValue().set(shortCode, dbMapping.get());
                    return dbMapping.get();
                } else {
                    metrics.recordInvalidShortCode();
                    return null;
                }
            } catch (Exception e) {
                metrics.recordInvalidShortCode();
                return null;
            }
        });
    }

    public void sendClickEvent(String shortCode) {
        try {
            UrlClickedEvent event = new UrlClickedEvent(shortCode, LocalDateTime.now());
            kafkaTemplate.send("url-clicks", event);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize event", e);
        }
    }
}