package com.bookmymovie.events.service;


import com.bookmymovie.events.dto.request.ScreenCreateDto;
import com.bookmymovie.events.dto.request.ScreenLayoutCreateDto;
import org.springframework.http.ResponseEntity;

public interface ScreenService {

    ResponseEntity<?> createScreen(ScreenCreateDto screenCreateDto, String userId);

    ResponseEntity<?> createSeatLayout(ScreenLayoutCreateDto layout);
}
