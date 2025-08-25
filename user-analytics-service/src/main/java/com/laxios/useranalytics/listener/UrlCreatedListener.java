package com.laxios.useranalytics.listener;

import com.laxios.commons.events.UrlCreatedEvent;
import com.laxios.useranalytics.entity.Url;
import com.laxios.useranalytics.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UrlCreatedListener {

    private final UrlRepository urlRepository;

    @KafkaListener(topics = "url-created", groupId = "user-analytics-group")
    public void listen(UrlCreatedEvent event) {
        Url url = new Url();
        url.setShortCode(event.getShortCode());
        url.setCreatedBy(event.getCreatedBy());
        url.setCreatedAt(event.getCreatedAt());
        url.setClickCount(0L);
        url.setLastAccessed(null);

        urlRepository.save(url);

        System.out.println("Initialized analytics for new short URL: " + event);
    }
}
