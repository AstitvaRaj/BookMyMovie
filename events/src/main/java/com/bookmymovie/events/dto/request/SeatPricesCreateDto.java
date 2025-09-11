package com.bookmymovie.events.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SeatPricesCreateDto {

    UUID id;
    UUID seatCategoryId;
    Double price;
    UUID screenScheduleId;

}
