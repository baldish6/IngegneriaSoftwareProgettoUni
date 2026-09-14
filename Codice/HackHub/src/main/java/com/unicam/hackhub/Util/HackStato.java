package com.unicam.hackhub.Util;

import com.unicam.hackhub.Model.Hackathon;
import com.unicam.hackhub.Model.Mentore;
import com.unicam.hackhub.Model.Team;

public abstract class HackStato {

    Hackathon hackathon;

    public HackStato(Hackathon hackathon) {
        this.hackathon = hackathon;
    }

    public abstract Boolean canGiveValutazione();
    public abstract void iscriviHackathon(Team team);
    public abstract Boolean canChangeSottomissione();
    public abstract Boolean isActive();
    public abstract void addMentore(Mentore mentore);
    public abstract void declareWinner(Team team);

}
