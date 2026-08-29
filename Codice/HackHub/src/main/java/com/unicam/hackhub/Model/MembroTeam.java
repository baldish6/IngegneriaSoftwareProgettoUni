package com.unicam.hackhub.Model;

import java.util.Objects;

public class MembroTeam {
    private Integer id;
    private Utente utente;
    private Team team;

    public MembroTeam(Integer id, Utente utente, Team team) {
        this.id = id;
        this.utente = utente;
        this.team = team;
    }

    public Integer getId() {
        return id;
    }

    public Team getTeam() {
        return team;
    }

    public Utente getUtente() {
        return utente;
    }
/*
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MembroTeam that = (MembroTeam) o;
        return Objects.equals(id, that.id) && Objects.equals(utente, that.utente) && Objects.equals(team, that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, utente, team);
    }

    @Override
    public String toString() {
        return "MembroTeam{" +
                "id=" + id +
                ", utente=" + utente +
                ", team=" + team.getNome() +
                '}';
    }*/
}
