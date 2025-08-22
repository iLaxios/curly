package com.laxios.shortener.repository;

import com.laxios.shortener.entity.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {

    Optional<UrlMapping> findByOriginalUrlAndCreatedByUser(String originalUrl, String createdByUser);

    Optional<UrlMapping> findByShortCode(String shortCode);
}
