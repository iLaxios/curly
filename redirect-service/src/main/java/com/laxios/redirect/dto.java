package com.laxios.redirect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlMapping {
    private Long id;            // optional
    private String originalUrl;
    private String shortCode;
}

