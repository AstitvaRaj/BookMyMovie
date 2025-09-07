package com.bookmymovie.events.service.impl;

import com.bookmymovie.events.dto.request.AddVenueDto;
import com.bookmymovie.events.entities.Users;
import com.bookmymovie.events.entities.VenueEntity;
import com.bookmymovie.events.entities.enums.AccountStatus;
import com.bookmymovie.events.repository.UsersRepository;
import com.bookmymovie.events.repository.VenueRepository;
import com.bookmymovie.events.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class VenueServiceImpl implements VenueService {

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    UsersRepository usersRepository;

    @Override
    public ResponseEntity<?> changeOwner(AddVenueDto venue, String userId) {
        try {
            Optional<VenueEntity> venueOpt = venueRepository.findById(venue.getVenueId());
            if (venueOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Venue Id");
            }
            Optional<Users> ownerOpt = usersRepository.findByUsername(venue.getOwnerUsername());
            if (ownerOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Username");
            }
            Users users = ownerOpt.get();
            if (!users.getAccountStatus().equals(AccountStatus.ACTIVE)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Not Active");
            }
            VenueEntity venueEntity = venueOpt.get();
            venueEntity.setOwner(users.getUserId());
            venueRepository.save(venueEntity);
            return ResponseEntity.ok("Owner updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> createVenue(AddVenueDto venue, String userId) {
        try {
            VenueEntity venueEntity = new VenueEntity();
            if (venue.getVenueId() != null) {
                Optional<VenueEntity> venueOpt = venueRepository.findById(venue.getVenueId());
                if (venueOpt.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid Venue Id");
                }
            }
            VenueEntity save = venueRepository.save(venue.getVenueEntity(venueEntity, userId));
            return ResponseEntity.ok(Map.of("msg", venue.getVenueId() == null ? "Venue Created Successfully" : "Venue Updated Successfully", "data", Map.of("id", save.getId())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
