package com.laxios.useranalytics.listener;

import com.laxios.commons.events.UrlClickedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UrlClickListener {

    @KafkaListener(topics = "url-clicks", groupId = "user-analytics-group")
    public void listen(UrlClickedEvent event) {
        System.out.println("Received URL Click Event: " + event);
    }
}
