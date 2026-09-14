package com.unicam.hackhub.Util;

import com.unicam.hackhub.Model.Hackathon;
import com.unicam.hackhub.Model.Mentore;
import com.unicam.hackhub.Model.Team;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import javax.management.OperationsException;

@Entity
public abstract class HackStato {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    Hackathon hackathon;

    public HackStato(Hackathon hackathon) {
        this.hackathon = hackathon;
    }

    public HackStato() {}

    public abstract Boolean canGiveValutazione() throws OperationsException;
    public abstract void iscriviHackathon(Team team) throws OperationsException;
    public abstract Boolean canChangeSottomissione() throws OperationsException;
    public abstract Boolean isActive() throws OperationsException;
    public abstract Boolean addMentore(Mentore mentore) throws OperationsException;
    public abstract void declareWinner(Team team) throws OperationsException;

}
