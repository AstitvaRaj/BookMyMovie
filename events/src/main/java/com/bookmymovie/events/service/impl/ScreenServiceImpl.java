package com.bookmymovie.events.service.impl;

import com.bookmymovie.events.dto.enums.ScreenLayoutStatus;
import com.bookmymovie.events.dto.request.ScreenCreateDto;
import com.bookmymovie.events.dto.request.ScreenLayoutCreateDto;
import com.bookmymovie.events.entities.Screens;
import com.bookmymovie.events.entities.SeatMap;
import com.bookmymovie.events.repository.ScreensRepository;
import com.bookmymovie.events.repository.SeatMapRepository;
import com.bookmymovie.events.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.InvalidPropertiesFormatException;

@Service
public class ScreenServiceImpl implements ScreenService {

    @Autowired
    ScreensRepository screensRepository;

    @Autowired
    SeatMapRepository seatMapRepository;

    @Override
    public ResponseEntity<?> createScreen(ScreenCreateDto screenCreateDto, String userId) {
        try {
            Screens entity = screenCreateDto.getEntity();
            if (!screensRepository.findByVenueAndScreenNameIgnoreCaseAndStatus(entity.getVenue(), entity.getScreenName(), entity.getStatus()).isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Screen Name should be Unique");
            }
            screensRepository.save(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Screen Created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> createSeatLayout(ScreenLayoutCreateDto layout) {
        try {
            long l = seatMapRepository.countByStatusAndScreenId(ScreenLayoutStatus.ACTIVE, layout.getScreenId());
            if(l>0){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Layout is already defined for this screen");
            }
            SeatMap seatMapEntity = layout.getSeatMapEntity(ScreenLayoutStatus.ACTIVE);
            seatMapRepository.save(seatMapEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Layout Created Successfully");
        } catch (InvalidPropertiesFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
