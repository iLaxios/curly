package com.laxios.analytics.listener;

import com.laxios.commons.events.UrlClickedEvent;
import com.laxios.analytics.entity.Url;
import com.laxios.analytics.repository.UrlRepository;
import lombok.Data;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Data
public class UrlClickListener {

    private final UrlRepository urlRepository;

    @KafkaListener(topics = "url-clicks", groupId = "user-analytics-group")
    public void listen(UrlClickedEvent event) {
        urlRepository.findByShortCode(event.getShortCode())
                .ifPresentOrElse(existing -> {
                    existing.setClickCount(existing.getClickCount() + 1);
                    existing.setLastAccessed(event.getTimestamp());
                    urlRepository.save(existing);
                }, () -> {
                    Url analytics = new Url();
                    analytics.setShortCode(event.getShortCode());
                    analytics.setClickCount(1L);
                    analytics.setLastAccessed(event.getTimestamp());
                    urlRepository.save(analytics);
                });

        System.out.println("Processed URL Click Event: " + event);
    }
}
