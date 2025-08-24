package com.laxios.useranalytics.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "url_analytics")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UrlAnalyticsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shortCode;
    private Long clickCount;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime lastAccessed;
}
