package com.laxios.useranalytics.entity;

import jakarta.persistence.*;
import lombok.*;

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

    private java.time.LocalDateTime lastAccessed;
}
