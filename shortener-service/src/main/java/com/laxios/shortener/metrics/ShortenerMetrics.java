package com.laxios.shortener.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Service
public class ShortenerMetrics {

    private final Counter requestCounter;
    private final Counter createdCounter;
    private final Counter failedCounter;
    private final Timer creationLatency;

    public ShortenerMetrics(MeterRegistry registry) {
        this.requestCounter = Counter.builder("url_shortener_requests_total")
                .description("Total shorten requests received")
                .register(registry);

        this.createdCounter = Counter.builder("url_shortener_created_total")
                .description("Successfully shortened URLs")
                .register(registry);

        this.failedCounter = Counter.builder("url_shortener_failed_total")
                .description("Failed URL shorten requests")
                .register(registry);

        this.creationLatency = Timer.builder("url_shortener_creation_latency_seconds")
                .publishPercentileHistogram()
                .description("Time taken to shorten a URL")
                .register(registry);
    }

    public void recordRequest() { requestCounter.increment(); }
    public void recordSuccess() { createdCounter.increment(); }
    public void recordFailure() { failedCounter.increment(); }
    public <T> T recordLatency(Supplier<T> supplier) { return creationLatency.record(supplier); }
}
