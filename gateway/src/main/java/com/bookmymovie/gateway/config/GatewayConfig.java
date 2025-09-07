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
                .route("movie_service", r -> r
                        .path("/api/**") // Incoming path
                        .filters(f -> f.stripPrefix(1)) // Remove /movies before forwarding
                        .uri("http://images.pexels.com/photos/45201/kitty-cat-kitten-pet-45201.jpeg")) // Downstream service
                .build();
    }
}
