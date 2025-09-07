package com.bookmymovie.events.service;

import com.bookmymovie.events.dto.request.AddVenueDto;
import org.springframework.http.ResponseEntity;

public interface VenueService {

    ResponseEntity<?> changeOwner(AddVenueDto venue, String userId);

    ResponseEntity<?> createVenue(AddVenueDto venue, String userId);
}
