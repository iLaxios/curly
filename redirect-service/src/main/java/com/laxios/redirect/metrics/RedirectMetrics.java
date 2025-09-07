package com.laxios.redirect.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

@Component
public class RedirectMetrics {

    private final Counter requests;
    private final Counter cacheHits;
    private final Counter cacheMisses;
    private final Counter invalidShortCodes;
    private final Timer redirectLatency;

    public RedirectMetrics(MeterRegistry registry) {
        this.requests = Counter.builder("redirect_requests_total")
                .description("Total redirect requests")
                .register(registry);

        this.cacheHits = Counter.builder("redirect_cache_hits_total")
                .description("Cache hits for short code lookups")
                .register(registry);

        this.cacheMisses = Counter.builder("redirect_cache_misses_total")
                .description("Cache misses for short code lookups")
                .register(registry);

        this.invalidShortCodes = Counter.builder("redirect_invalid_shortcodes_total")
                .description("Redirects attempted for unknown short codes")
                .register(registry);

        this.redirectLatency = Timer.builder("redirect_latency_seconds")
                .description("Time taken to resolve redirects")
                .publishPercentileHistogram()
                .register(registry);
    }

    public void recordRequest() { requests.increment(); }
    public void recordCacheHit() { cacheHits.increment(); }
    public void recordCacheMiss() { cacheMisses.increment(); }
    public void recordInvalidShortCode() { invalidShortCodes.increment(); }

    public <T> T recordLatency(Supplier<T> supplier) {
        return redirectLatency.record(supplier);
    }
}
