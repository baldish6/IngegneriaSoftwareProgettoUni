package com.unicam.hackhub.Model;

import jakarta.persistence.Entity;

@Entity
public class Mentore extends Utente{

    public Mentore(String nome, String password) {
        super( nome, password, Ruolo.MENTORE);
    }
    public Mentore() {}
}
