package com.bookmymovie.events.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SeatLayout {

    private Integer row;

    private Integer columns;

    private String seatsCategory;

    private boolean blank = false;

    private UUID layoutId = UUID.randomUUID();

    private Character rowStart;

    private Character rowEnd;

    private Integer colStart;

    private Integer colEnd;

}
