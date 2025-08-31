package com.laxios.analytics.controller;

import com.laxios.analytics.dto.UrlAnalyticsDto;
import com.laxios.analytics.service.UrlAnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
public class UrlAnalyticsController {

    private final UrlAnalyticsService service;

    @GetMapping("/{shortCode}")
    public UrlAnalyticsDto getByShortCode(@PathVariable String shortCode) {
        return service.getByShortCode(shortCode);
    }

    @GetMapping("/user/{createdBy}")
    public List<UrlAnalyticsDto> getByUser(@PathVariable String createdBy) {
        return service.getByUser(createdBy);
    }
}

