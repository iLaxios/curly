package com.laxios.commons.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlCreatedEvent {
    private String shortCode;
    private String createdBy;
    private LocalDateTime createdAt;
}
