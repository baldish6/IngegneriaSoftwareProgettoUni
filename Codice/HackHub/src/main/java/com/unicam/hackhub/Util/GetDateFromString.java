package com.unicam.hackhub.Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class GetDateFromString {
    private static DateTimeFormatter dateformatter
            = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static LocalDate getLocalDate(String date){
      return   Objects.requireNonNull(LocalDate.parse(date,dateformatter));
    }

}


