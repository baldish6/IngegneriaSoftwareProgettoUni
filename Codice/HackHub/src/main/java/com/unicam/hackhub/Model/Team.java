package com.unicam.hackhub.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Team {
    private Integer id;
    private String nome;
    private Set<MembroTeam> membroTeams = new HashSet<>();
    private ArrayList<Hackathon>  hackathonsIscritti = new ArrayList<>();

    public Team(Integer id, String nome, Utente utente) {
        this.id = id;
        this.nome = nome;
        MembroTeam  membroTeam = new MembroTeam(utente.getId(),utente,this);

        membroTeams.add(membroTeam);
    }

    public void addHackathonIscritti(Hackathon hackathon) {
        this.hackathonsIscritti.add(hackathon);
    }

    public Integer getId() {
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
