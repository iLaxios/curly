package com.laxios.useranalytics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UrlAnalyticsDto {
    private String shortCode;
    private Long clickCount;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime lastAccessed;
}
