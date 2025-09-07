package com.bookmymovie.events.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeatLayout {

    private Integer row;

    private Integer columns;

    private String seatsCategory;

    private boolean blank = false;

    private UUID layoutId;

    public SeatLayout(Integer row, Integer columns, String seatsCategory, boolean blank) {
        this.row = row;
        this.columns = columns;
        this.seatsCategory = seatsCategory;
        this.blank = blank;
        layoutId = UUID.randomUUID();
        System.out.println("layout " + this);
    }

}
