package com.unicam.hackhub.Model;

import jakarta.persistence.*;

@Entity
public class Richiesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Team team;

    @ManyToOne
    private Mentore mentore;

    private String messaggio;

    public Richiesta(Team team, Mentore mentore, String messaggio) {
        this.team = team;
        this.mentore = mentore;
        this.messaggio = messaggio;
    }

    public Richiesta() {}

    public Team getTeam() {
        return team;
    }

    public Long getId() {
        return id;
    }

    public Mentore getMentore() {
        return mentore;
    }

    public String getMessaggio() {
        return messaggio;
    }



    @Override
    public String toString() {
        return "Richiesta{" +
                "id=" + id +
                ", team=" + team +
                ", mentore=" + mentore +
                ", messaggio='" + messaggio + '\'' +
                '}';
    }
}
