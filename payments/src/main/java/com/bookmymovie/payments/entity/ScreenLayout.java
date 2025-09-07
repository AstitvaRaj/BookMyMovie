package com.bookmymovie.payments.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScreenLayout {

    private Integer length;

    private Integer breadth;

    private List<SeatLayout> seatLayout;

    public boolean isValidLayout(){
        Integer totalNoOfCells = length * breadth;
        Integer layoutCells = 0;
        for (SeatLayout layout : seatLayout) {
            layoutCells += layout.getRow() * layout.getColumns();
        }
        return totalNoOfCells.equals(layoutCells);
    }
}
