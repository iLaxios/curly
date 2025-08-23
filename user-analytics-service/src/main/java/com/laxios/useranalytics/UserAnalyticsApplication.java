package com.laxios.useranalytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class UserAnalyticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserAnalyticsApplication.class, args);
    }
}