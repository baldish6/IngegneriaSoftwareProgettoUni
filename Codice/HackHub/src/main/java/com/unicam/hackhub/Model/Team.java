package com.unicam.hackhub.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

   private List<String> messaggi = new ArrayList<>();

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

    public void messageCall(Mentore mentore, LocalDate date) {
        DateTimeFormatter dateformatter
                = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String messagio = "Il mentore "+ mentore.getNome() + "con id "+ mentore.getId()+
                " ha creato un incontro il giorno "+ date.format(dateformatter);

        messaggi.add(messagio);

    }

    public void avverti(String messaggio) {
        String newmessaggio = "L'organizzatore ha mandato un'avvertimento : "+messaggio;
        messaggi.add(newmessaggio);
    }

    public void quitTeam(MembroTeam membroTeam) {
        membroTeams.remove(membroTeam);
    }

    public Boolean isTeamEmpty() {
        return membroTeams.isEmpty();
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

    public Set<MembroTeam> sospendi(){
        return membroTeams;
    }

    public Integer getSize() {
        return membroTeams.size();
    }

    public Set<Hackathon> getHackathonsIscritti() {
        return hackathonsIscritti;
    }

    public List<String> getMessaggi() {
        return messaggi;
    }
}
