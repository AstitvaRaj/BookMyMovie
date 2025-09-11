package com.bookmymovie.events.dto.request;

import com.bookmymovie.events.dto.enums.ScreenLayoutStatus;
import com.bookmymovie.events.entities.ScreenLayout;
import com.bookmymovie.events.entities.SeatMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.InvalidPropertiesFormatException;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ScreenLayoutCreateDto {

    private UUID id;
    private UUID screenId;
    private ScreenLayout layout;
    private ScreenLayoutStatus status;

    public SeatMap getSeatMapEntity() throws InvalidPropertiesFormatException {
        if(!layout.isValidLayout()){
            throw  new InvalidPropertiesFormatException("Invalid Seat Layout!");
        }
        SeatMap seatMap = new SeatMap();
        seatMap.setId(id);
        seatMap.setScreenId(screenId);
        seatMap.setLayout(layout);
        seatMap.setStatus(status);
        return seatMap;
    }

    public SeatMap getSeatMapEntity(ScreenLayoutStatus status) throws InvalidPropertiesFormatException {
        SeatMap seatMapEntity = this.getSeatMapEntity();
        seatMapEntity.setStatus(status);
        return seatMapEntity;
    }

}
