package com.bookmymovie.events.service.impl;

import com.bookmymovie.events.dto.enums.EventScheduledStatus;
import com.bookmymovie.events.dto.request.ScheduleEventDto;
import com.bookmymovie.events.dto.request.ScreenTimings;
import com.bookmymovie.events.dto.request.SeatPricing;
import com.bookmymovie.events.dto.request.UpdateSeatPricesDto;
import com.bookmymovie.events.entities.EventLanguage;
import com.bookmymovie.events.entities.ScreenSchedule;
import com.bookmymovie.events.entities.Screens;
import com.bookmymovie.events.entities.SeatPricingEntity;
import com.bookmymovie.events.repository.EventLanguageRepository;
import com.bookmymovie.events.repository.ScreenScheduleRepository;
import com.bookmymovie.events.repository.ScreensRepository;
import com.bookmymovie.events.repository.SeatPricingRepository;
import com.bookmymovie.events.service.ScheduleService;
import com.google.api.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    EventLanguageRepository eventLanguageRepo;

    @Autowired
    ScreenScheduleRepository screenScheduleRepository;

    @Autowired
    ScreensRepository screensRepository;

    @Autowired
    SeatPricingRepository seatPricingRepository;

    @Override
    public ResponseEntity<?> scheduleEvent(List<ScheduleEventDto> eventDto) {
        try {
            List<ScreenSchedule> list = new LinkedList<>();
            for (ScheduleEventDto scheduleEventDto : eventDto) {
                Optional<EventLanguage> byId = eventLanguageRepo.findById(scheduleEventDto.getEventId());
                if (byId.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid event Id");
                }
                EventLanguage eventLanguage = byId.get();
                Long durationInSeconds = eventLanguage.getEvents().getDuration();

                List<UUID> list1 = scheduleEventDto.getTimings().stream().map(ScreenTimings::getScreenId).toList();
                List<Screens> allScreens = screensRepository.findAllById(list1);
//                List<List<Date>> datesList = scheduleEventDto.getTimings().stream().map(ScreenTimings::getStartTime).toList();

                for (int i = 0; i < allScreens.size(); i++) {
                    Screens screens = allScreens.get(i);
                    ScreenTimings screenTimings = scheduleEventDto.getTimings().get(i);
                    for (SeatPricing seatPricing : screenTimings.getStartTime()) {
                        Date startDate = seatPricing.getDate();
                        ScreenSchedule schedule = new ScreenSchedule();
                        schedule.setEvents(eventLanguage);
                        schedule.setScreens(screens);
                        schedule.setStartTime(startDate);
                        schedule.setEndTime(Date.from(Instant.ofEpochSecond(startDate.toInstant().getEpochSecond() + durationInSeconds)));
                        schedule.setStatus(EventScheduledStatus.SCHEDULED);
                        List<SeatPricingEntity> list2 = seatPricing.getPrices().stream().map(e -> {
                            SeatPricingEntity entity = new SeatPricingEntity();
                            entity.setPricing(e.getPrice());
                            entity.setActiveFlag(true);
                            entity.setCreatedDate(new Date());
                            entity.setSeatCategoryId(e.getCategoryId());
                            entity.setScreenSchedule(schedule);
                            return entity;
                        }).toList();
                        schedule.setSeatPricing(list2);
                        list.add(schedule);
                    }
                }
            }
            screenScheduleRepository.saveAll(list);
            return ResponseEntity.status(HttpStatus.CREATED).body("Scheduled Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @Override
    public ResponseEntity<?> updateSeatPrices(List<UpdateSeatPricesDto> updateSeatPricesDto) {
        try{
            List<SeatPricingEntity> pricingEntities = new ArrayList<>();
            for (UpdateSeatPricesDto seatPricesDto : updateSeatPricesDto) {
                Optional<SeatPricingEntity> byId = seatPricingRepository.findById(seatPricesDto.getId());
                if(byId.isEmpty()){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Id");
                }
                SeatPricingEntity seatPricingEntity = byId.get();
                seatPricingEntity.setActiveFlag(seatPricesDto.getActiveFlag());
                seatPricingEntity.setPricing(seatPricesDto.getPrice());
                seatPricingEntity.setSeatCategoryId(seatPricesDto.getSeatCategoryId());
                pricingEntities.add(seatPricingEntity);
            }
            seatPricingRepository.saveAll(pricingEntities);
            return ResponseEntity.ok("Updated Successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
