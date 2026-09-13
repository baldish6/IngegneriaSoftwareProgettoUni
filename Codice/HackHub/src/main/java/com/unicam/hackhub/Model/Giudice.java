package com.unicam.hackhub.Model;

import jakarta.persistence.Entity;

@Entity
public class Giudice extends Utente {
    public Giudice(String nome, String password) {
        super(nome, password,Ruolo.GIUDICE);
    }
    public Giudice() {}
}
