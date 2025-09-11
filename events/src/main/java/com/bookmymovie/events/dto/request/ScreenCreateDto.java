package com.bookmymovie.events.dto.request;


import com.bookmymovie.events.entities.Screens;
import com.bookmymovie.events.entities.enums.VenueStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ScreenCreateDto {

    private UUID id;
    private String screenName;
    private UUID venueId;
    private VenueStatus status;
    private Integer screenType;

    public Screens getEntity(){
        Screens screens = new Screens();
        screens.setCreatedDate(new Date());
        screens.setScreenName(screenName);
        screens.setScreenType(screenType);
        screens.setId(id);
        screens.setVenue(venueId);
        screens.setStatus(VenueStatus.ACTIVE);
        return screens;
    }
}
