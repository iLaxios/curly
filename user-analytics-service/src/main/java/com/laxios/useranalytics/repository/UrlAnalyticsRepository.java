package com.laxios.useranalytics.repository;

import com.laxios.useranalytics.entity.UrlAnalyticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UrlAnalyticsRepository extends JpaRepository<UrlAnalyticsEntity, Long> {
    Optional<UrlAnalyticsEntity> findByShortCode(String shortCode);
    List<UrlAnalyticsEntity> findByCreatedBy(String createdBy);
}
