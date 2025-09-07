package com.bookmymovie.gateway.config;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

//@Component("inMemoryRateLimiter")
public class InMemoryRateLimiter implements RateLimiter<InMemoryRateLimiter.Config> {

    private final Map<String, Window> counters = new ConcurrentHashMap<>();

    @Override
    public Mono<Response> isAllowed(String routeId, String key) {
        Config config = getConfig().getOrDefault(routeId, new Config(1, 1));
        long now = Instant.now().getEpochSecond();

        Window window = counters.computeIfAbsent(key, k -> new Window(now, new AtomicInteger(10)));

        synchronized (window) {
            if (now - window.startTime >= config.windowInSeconds) {
                window.startTime = now;
                window.counter.set(0);
            }

            if (window.counter.incrementAndGet() > config.requests) {
                return Mono.just(new Response(false, Map.of("X-RateLimit-Remaining", "0")));
            }

            return Mono.just(new Response(true, Map.of(
                    "X-RateLimit-Remaining",
                    String.valueOf(config.requests - window.counter.get())
            )));
        }
    }

    @Override
    public Map<String, Config> getConfig() {
        // You can hardcode per-route config here if needed
        return Map.of("movie_service", new Config(5, 10)); // 5 requests per 10 seconds
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    @Override
    public Config newConfig() {
        return new Config();
    }

    public static class Config {
        public int requests;
        public int windowInSeconds;

        public Config() {}

        public Config(int requests, int windowInSeconds) {
            this.requests = requests;
            this.windowInSeconds = windowInSeconds;
        }
    }

    private static class Window {
        long startTime;
        AtomicInteger counter;

        Window(long startTime, AtomicInteger counter) {
            this.startTime = startTime;
            this.counter = counter;
        }
    }
}