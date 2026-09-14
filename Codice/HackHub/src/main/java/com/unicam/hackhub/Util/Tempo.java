package com.unicam.hackhub.Util;


import com.unicam.hackhub.Repository.TimeListenerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class Tempo implements ITempo {
    private static Tempo instance;

    private LocalDate timeNow = LocalDate.now();

    private TimeListenerRepository  timeListenerRepository;

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
        timeListenerRepository.findAll().forEach(x->x.update(timeNow));
    }

    @Override
    public void subscribe(ITimeListener listener) {
        timeListenerRepository.save(listener);
    }

    @Override
    public void unsubscribe(ITimeListener listener) {
        timeListenerRepository.delete(listener);
    }





}
