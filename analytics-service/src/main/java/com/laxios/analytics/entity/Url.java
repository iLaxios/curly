package com.laxios.analytics.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "url_analytics")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String shortCode;

    private Long clickCount = 0L;

    private String createdBy;

    private LocalDateTime createdAt;

    private LocalDateTime lastAccessed;
}
