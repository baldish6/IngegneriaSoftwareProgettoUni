package com.unicam.hackhub.Util;

import com.unicam.hackhub.Model.Hackathon;
import com.unicam.hackhub.Model.Mentore;
import com.unicam.hackhub.Model.Team;

import javax.management.OperationsException;

public class inValutazione extends HackStato{

    public inValutazione(Hackathon hackathon) {
        super(hackathon);
    }

    @Override
    public Boolean canGiveValutazione() {
        return true;
    }

    @Override
    public void iscriviHackathon(Team team) throws OperationsException {
        throw new OperationsException();
    }

    @Override
    public Boolean canChangeSottomissione() throws OperationsException {
        throw new OperationsException();
    }

    @Override
    public Boolean isActive() {
        return true;
    }

    @Override
    public Boolean addMentore(Mentore mentore) {
        return hackathon.changeMentoreList(mentore);
    }

    @Override
    public void declareWinner(Team team)  {
        hackathon.addWinner(team);
    }
}
