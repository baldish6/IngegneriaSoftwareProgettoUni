package com.unicam.hackhub.Util;

import com.unicam.hackhub.Model.Hackathon;
import com.unicam.hackhub.Model.Mentore;
import com.unicam.hackhub.Model.Team;

public class inIscrizione extends HackStato{
    public inIscrizione(Hackathon hackathon) {
        super(hackathon);
    }

    @Override
    public Boolean canGiveValutazione() {
        return null;
    }

    @Override
    public void iscriviHackathon(Team team) {

    }

    @Override
    public Boolean canChangeSottomissione() {
        return null;
    }

    @Override
    public Boolean isActive() {
        return null;
    }

    @Override
    public void addMentore(Mentore mentore) {

    }

    @Override
    public void declareWinner(Team team) {

    }
}
