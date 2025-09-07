package com.bookmymovie.payments.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    String host;

    @Value("${spring.redis.port}")
    Long port;

//    @Bean
    RedisTemplate<String, Object> getRedisTemplate(){
        RedisTemplate<String, Object> stringObjectRedisTemplate = new RedisTemplate<>();

        return stringObjectRedisTemplate;
    }



}
