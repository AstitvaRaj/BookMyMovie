package com.bookmymovie.events.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyValue {

    @Value("${spring.jwt.secret}")
    private String jwtSecret;

}
