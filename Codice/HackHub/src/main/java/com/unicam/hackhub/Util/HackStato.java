package com.unicam.hackhub.Util;

import com.unicam.hackhub.Model.Hackathon;
import com.unicam.hackhub.Model.Mentore;
import com.unicam.hackhub.Model.Team;
import jakarta.persistence.*;

import javax.management.OperationsException;

@Entity
public abstract class HackStato {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    Hackathon hackathon;

    public HackStato(Hackathon hackathon) {
        this.hackathon = hackathon;
    }

    public HackStato() {}

    public Long getId() {
        return id;
    }


    public abstract Boolean canGiveValutazione() throws OperationsException;
    public abstract void iscriviHackathon(Team team) throws OperationsException;
    public abstract Boolean canChangeSottomissione() throws OperationsException;
    public abstract Boolean isActive() throws OperationsException;
    public abstract Boolean addMentore(Mentore mentore) throws OperationsException;
    public abstract void declareWinner(Team team) throws OperationsException;

}
