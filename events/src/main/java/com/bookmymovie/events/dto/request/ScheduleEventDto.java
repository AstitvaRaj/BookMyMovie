package com.bookmymovie.events.dto.request;

import com.bookmymovie.events.entities.ScreenSchedule;
import com.bookmymovie.events.service.ScheduleService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ScheduleEventDto {
    UUID eventId;
    List<ScreenTimings> timings;
}


