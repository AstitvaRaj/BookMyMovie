package com.bookmymovie.events.dto.request;

import com.bookmymovie.events.entities.VenueEntity;
import com.bookmymovie.events.entities.enums.VenueStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class AddVenueDto {

    private UUID venueId;
    private String name;
    private UUID cityId;
    private String ownerUsername;
    private VenueStatus venueStatus;

    public VenueEntity getVenueEntity(VenueEntity entity, String userId) {
        entity.setId(this.venueId);
        entity.setName(this.name);
        entity.setCity(this.cityId);
        entity.setStatus(venueStatus);
        if (this.venueId == null) {
            entity.setOwner(UUID.fromString(userId));
        }
        return entity;
    }


}
