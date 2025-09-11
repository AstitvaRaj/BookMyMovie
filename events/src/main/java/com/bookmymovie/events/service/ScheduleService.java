package com.bookmymovie.events.service;

import com.bookmymovie.events.dto.request.ScheduleEventDto;
import com.bookmymovie.events.dto.request.UpdateSeatPricesDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ScheduleService {

    ResponseEntity<?> scheduleEvent(List<ScheduleEventDto> eventDto);

    ResponseEntity<?> updateSeatPrices(List<UpdateSeatPricesDto> updateSeatPricesDto);
}
