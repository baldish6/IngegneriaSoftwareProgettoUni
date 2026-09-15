package com.unicam.hackhub.Util;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class Tempo implements ITempo {
    private static Tempo instance;

    private LocalDate timeNow = LocalDate.now();

    private ArrayList<ITimeListener> timeListenerList= new ArrayList<ITimeListener>();

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
    //@Transactional
    public void changeTime(LocalDate timeNow) {
        this.timeNow = timeNow;
        timeListenerList.forEach(listener -> listener.update(timeNow));

    }

    @Override
    public void subscribe(ITimeListener listener) {
        timeListenerList.add(listener);
    }

    @Override
    public void unsubscribe(ITimeListener listener) {
       timeListenerList.remove(listener);
    }






}
