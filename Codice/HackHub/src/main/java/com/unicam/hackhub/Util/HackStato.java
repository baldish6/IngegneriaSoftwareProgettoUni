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
    protected Long id;

    @ManyToOne // old
    //@OneToOne  // new
    protected Hackathon hackathon;

    public HackStato(Hackathon hackathon) {
        this.hackathon = hackathon;
    }

    public HackStato() {}

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public Hackathon getHackathon() {
        return hackathon;
    }

    public abstract Boolean canGiveValutazione();
    public abstract void iscriviHackathon(Team team);
    public abstract Boolean canChangeSottomissione();
    public abstract Boolean isActive() ;
    public abstract Boolean addMentore(Mentore mentore) ;
    public abstract void declareWinner(Team team) ;

}
