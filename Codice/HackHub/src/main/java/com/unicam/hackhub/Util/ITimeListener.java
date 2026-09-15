package com.unicam.hackhub.Util;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;



public interface ITimeListener {
    void update(LocalDate time);
}
