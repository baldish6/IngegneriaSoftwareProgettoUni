package com.unicam.hackhub.Util;


import java.time.LocalDate;

public class Tempo implements ITempo {
    private static Tempo instance;

    private LocalDate timeNow = LocalDate.now();

    private Tempo() {}

    public static Tempo getInstance() {
        if (instance == null) {
            instance = new Tempo();
        }
        return instance;
    }

    @Override
    public LocalDate getTime() {
        return timeNow;
    }

    @Override
    public void changeTime(LocalDate timeNow) {
        this.timeNow = timeNow;
    }
}
