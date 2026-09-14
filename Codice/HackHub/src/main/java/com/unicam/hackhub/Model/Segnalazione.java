package com.unicam.hackhub.Model;

import jakarta.persistence.*;

@Entity
public class Segnalazione {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Team team;

    @ManyToOne
    private Mentore mentore;

    private String messaggio;

    public Segnalazione(Team team, Mentore mentore, String messaggio) {
        this.team = team;
        this.mentore = mentore;
        this.messaggio = messaggio;
    }

    public Segnalazione() {}

    public Long getId() {
        return id;
    }

    public Team getTeam() {
        return team;
    }

    public Mentore getMentore() {
        return mentore;
    }

    public String getMessaggio() {
        return messaggio;
    }

    @Override
    public String toString() {
        return "Segnalazione{" +
                "id=" + id +
                ", team=" + team +
                ", mentore=" + mentore +
                ", messaggio='" + messaggio + '\'' +
                '}';
    }
}
