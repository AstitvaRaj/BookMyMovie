package com.bookmymovie.payments.utility;

import io.lettuce.core.RedisException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.*;

@Component
public class RedisUtility {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public Map<String, String> findByPrefix(String prefix) {
        ScanOptions options = ScanOptions.scanOptions().match(prefix + "*").build();
        List<String> keys = new ArrayList<>();
        try (Cursor<String> cursor = redisTemplate.scan(options)) {
            while (cursor.hasNext()) keys.add(cursor.next());
        }
        if (keys.isEmpty()) return Collections.emptyMap();
        List<String> values = redisTemplate.opsForValue().multiGet(keys);
        Map<String, String> out = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            out.put(keys.get(i), values.get(i));
        }
        return out;
    }

}
