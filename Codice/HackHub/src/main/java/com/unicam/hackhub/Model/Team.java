package com.unicam.hackhub.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Team {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    @ManyToOne
    private Set<MembroTeam> membroTeams = new HashSet<>();
    @ManyToMany
    private ArrayList<Hackathon>  hackathonsIscritti = new ArrayList<>();

    public Team( String nome, Utente utente) {
        this.nome = nome;
        MembroTeam  membroTeam = new MembroTeam(utente,this);
        membroTeams.add(membroTeam);
    }

    public Team() {}

    public void addHackathonIscritti(Hackathon hackathon) {
        this.hackathonsIscritti.add(hackathon);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Set<MembroTeam> getMembroTeams() {
        return membroTeams;
    }

    public Integer getSize() {
        return membroTeams.size();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) && Objects.equals(nome, team.nome) && Objects.equals(membroTeams, team.membroTeams) && Objects.equals(hackathonsIscritti, team.hackathonsIscritti);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, membroTeams, hackathonsIscritti);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", membroTeams=" + membroTeams +
               // ", hackathonsIscritti=" + hackathonsIscritti +
                '}';
    }
}
