package com.bookmymovie.events.dto.request;


import com.bookmymovie.events.dto.enums.EventStatus;
import com.bookmymovie.events.entities.EventLanguage;
import com.bookmymovie.events.entities.Events;
import com.bookmymovie.events.repository.LanguageMasterRepository;
import com.bookmymovie.events.utility.CustomDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CreateEventDto {
    String eventName;
    String description;
    Long eventCategory;
    Date releaseDate;
    Long duration;
    EventStatus status = EventStatus.SCHEDULED;
    List<Long> languages;

    public Events getEventEntity(UUID adminUuid) throws Exception{
        if(CustomDate.isGreater(Instant.now(), releaseDate.toInstant())){
            throw new Exception("Released date cannot be smaller that current date!");
        }
        Events events = new Events();
        events.setEventAdmin(adminUuid);
        events.setEventName(eventName);
        events.setEventCategory(eventCategory);
        events.setDescription(description);
        events.setReleaseDate(releaseDate);
        events.setDuration(duration);
        events.setStatus(status);
        return events;
    }

}

