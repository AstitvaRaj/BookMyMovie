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
public class UpdateSeatPricesDto {

    UUID id;
    Boolean activeFlag;
    Double price;
    UUID seatCategoryId;

}
