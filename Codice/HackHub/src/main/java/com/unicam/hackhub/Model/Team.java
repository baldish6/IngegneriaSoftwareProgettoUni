package com.unicam.hackhub.Model;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Team {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Set<MembroTeam> membroTeams = new HashSet<>();
    @ManyToMany
    private Set<Hackathon>  hackathonsIscritti = new HashSet<>();

   

    public Team( String nome) {
        this.nome = nome;
       // MembroTeam  membroTeam = new MembroTeam(utente,this);
        //membroTeams.add(membroTeam);
    }

    public Team() {}

    public Boolean addMembroTeam(Utente membro) {
        MembroTeam membroTeam = new MembroTeam(membro,this);
        return membroTeams.add(membroTeam);
    }


    public void addHackathonIscritti(Hackathon hackathon) {
        this.hackathonsIscritti.add(hackathon);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Hackathon> getHackathonsIscritti() {
        return hackathonsIscritti;
    }


}
