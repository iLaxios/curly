package com.laxios.redirect.service;

import com.laxios.redirect.entity.UrlMapping;
import com.laxios.redirect.repository.UrlMappingRepository;
import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Data
public class RedirectService {

    private final RedisTemplate<String, UrlMapping> redisTemplate;
    private final UrlMappingRepository urlMappingRepository;

    public UrlMapping getOriginalUrl(String shortCode) {
        UrlMapping mapping = redisTemplate.opsForValue().get(shortCode);
        if (mapping != null) return mapping;

        Optional<UrlMapping> dbMapping = urlMappingRepository.findByShortCode(shortCode);
        dbMapping.ifPresent(m -> redisTemplate.opsForValue().set(shortCode, m));
        return dbMapping.orElse(null);
    }
}