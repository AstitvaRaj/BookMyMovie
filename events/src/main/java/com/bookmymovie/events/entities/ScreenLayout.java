package com.bookmymovie.events.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScreenLayout {

    private Integer row;

    private Integer column;

    private List<SeatLayout> seatLayout;

    public boolean isValidLayout(){

        if (row == null || row <= 0 || column == null || column <= 0) {
            return false;
        }
        if (seatLayout == null || seatLayout.isEmpty()) {
            return false;
        }

        Set<String> occupied = new HashSet<>();

        for (SeatLayout sl : seatLayout) {
            if (sl.getRowStart() == null || sl.getRowEnd() == null ||
                    sl.getColStart() == null || sl.getColEnd() == null) {
                return false;
            }

            int startRow = sl.getRowStart() - 'A' + 1;
            int endRow   = sl.getRowEnd()   - 'A' + 1;

            if (startRow <= 0 || endRow > row || startRow > endRow) {
                return false;
            }
            if (sl.getColStart() <= 0 || sl.getColEnd() > column || sl.getColStart() > sl.getColEnd()) {
                return false;
            }

            // If not blank, must have category
            if (!sl.isBlank() && (sl.getSeatsCategory() == null || sl.getSeatsCategory().isEmpty())) {
                return false;
            }

            // Checking overlap in this loop
            for (int r = startRow; r <= endRow; r++) {
                for (int c = sl.getColStart(); c <= sl.getColEnd(); c++) {
                    String pos = r + "-" + c;
                    if (!occupied.add(pos)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
