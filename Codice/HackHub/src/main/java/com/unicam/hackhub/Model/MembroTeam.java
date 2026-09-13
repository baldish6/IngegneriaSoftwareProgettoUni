package com.unicam.hackhub.Model;


import jakarta.persistence.*;

@Entity
public class MembroTeam {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Utente utente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public MembroTeam(Utente utente, Team team) {
        this.utente = utente;
        this.team = team;
    }

    public MembroTeam() {}

    public Long getId() {
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
