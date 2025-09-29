package com.bookmymovie.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("event_service", r -> r
                        .path("/events/**") // Incoming path
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8082/"))
                .route("security_service", r -> r
                        .path("/secure/**") // Incoming path
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8080/"))
                .route("payment_service", r -> r
                        .path("/payment/**") // Incoming path
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8083/"))
                .build();
    }
}
