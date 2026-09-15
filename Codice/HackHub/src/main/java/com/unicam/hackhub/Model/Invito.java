package com.unicam.hackhub.Model;

import com.unicam.hackhub.Error.InvitoNotExistException;
import com.unicam.hackhub.Error.UtenteInvitatoException;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Invito {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    private Set<Team> listTeams = new HashSet<>();

    @ManyToOne
    private Utente utente;

    public Invito(Team team, Utente utente) {
        this.listTeams.add(team);
        this.utente = utente;
    }

    public Invito() {}


    public Set<Team> getListTeams() {
        return listTeams;
    }

    public Utente getUtente() {
        return utente;
    }

    public void addTeam(Team team) {
        if (!this.listTeams.contains(team)) {
            this.listTeams.add(team);
        }else {
            throw new UtenteInvitatoException();
        }
    }

    public void accettaInvito(Team team) {
        if (this.listTeams.contains(team)) {
           // this.listTeams.add(team);
            this.listTeams.remove(team);
            String messaggio = "Nuovo utente aggiunto "+utente.getNome()+" con id "+utente.getId();
            team.update(utente,messaggio);
            String messaggio2 = "L'utente "+utente.getNome()+" con id "+utente.getId()+" ha rifiutato l'invito";
            listTeams.forEach(x->x.update(null,messaggio2));
        }else {
            throw new InvitoNotExistException();
        }
    }




}
