package com.bookmymovie.events.utility;

import java.time.Instant;
import java.util.Date;

public class CustomDate extends Date {

    CustomDate() {
        super();
    }

    /**
     * @param instant1
     * @param instant2
     */
    public static boolean isGreater(Instant instant1, Instant instant2) {
        return instant1.getEpochSecond() > instant2.getEpochSecond();
    }

}
