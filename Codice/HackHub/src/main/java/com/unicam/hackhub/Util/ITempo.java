package com.unicam.hackhub.Util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ITempo {
    LocalDate getTime();

    void changeTime(LocalDate timeNow);

    void subscribe(ITimeListener listener);

    void unsubscribe(ITimeListener listener);

}
