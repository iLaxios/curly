package com.laxios.analytics.repository;

import com.laxios.analytics.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UrlAnalyticsRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortCode(String shortCode);
    List<Url> findByCreatedBy(String createdBy);
}
