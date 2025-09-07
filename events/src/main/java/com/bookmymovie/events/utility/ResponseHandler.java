package com.bookmymovie.events.utility;

import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    public static ResponseEntity<?> response() {
        return ResponseEntity.ok().build();
    }

}
