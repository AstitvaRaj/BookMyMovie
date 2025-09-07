package com.bookmymovie.events.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventDto {

    UUID eventsId;
    String eventName;
    String status;
    String description;

}
