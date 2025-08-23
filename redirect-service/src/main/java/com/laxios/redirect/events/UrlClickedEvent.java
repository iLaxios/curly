package com.laxios.redirect.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlClickedEvent {
    private String shortCode;
    private LocalDateTime timestamp;
}
