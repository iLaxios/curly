package com.laxios.analytics.service;

import com.laxios.analytics.dto.UrlAnalyticsDto;
import com.laxios.analytics.entity.Url;
import com.laxios.analytics.repository.UrlAnalyticsRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class UrlAnalyticsService {

    private final UrlAnalyticsRepository repository;

    public UrlAnalyticsService(UrlAnalyticsRepository repository) {
        this.repository = repository;
    }

    public UrlAnalyticsDto getByShortCode(String shortCode) {
        Url analytics = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("Not found: " + shortCode));

        return new UrlAnalyticsDto(
                analytics.getShortCode(),
                analytics.getClickCount(),
                analytics.getCreatedBy(),
                analytics.getCreatedAt(),
                analytics.getLastAccessed()
        );
    }

    public List<UrlAnalyticsDto> getByUser(String createdBy) {
        List<Url> list = repository.findByCreatedBy(createdBy);
        List<UrlAnalyticsDto> dtoList = new ArrayList<>();

        for (Url analytics : list) {
            dtoList.add(new UrlAnalyticsDto(
                    analytics.getShortCode(),
                    analytics.getClickCount(),
                    analytics.getCreatedBy(),
                    analytics.getCreatedAt(),
                    analytics.getLastAccessed()
            ));
        }

        return dtoList;
    }
}
