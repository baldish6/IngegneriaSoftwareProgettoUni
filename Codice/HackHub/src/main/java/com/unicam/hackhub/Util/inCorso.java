package com.unicam.hackhub.Util;

import com.unicam.hackhub.Model.Hackathon;
import com.unicam.hackhub.Model.Mentore;
import com.unicam.hackhub.Model.Team;

import javax.management.OperationsException;

public class inCorso extends HackStato{


    public inCorso(Hackathon hackathon) {
        super(hackathon);
    }

    @Override
    public Boolean canGiveValutazione() throws OperationsException {
        throw new OperationsException();
    }

    @Override
    public void iscriviHackathon(Team team) throws OperationsException {
        throw new OperationsException();
    }

    @Override
    public Boolean canChangeSottomissione() {
        return true;
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
    public void declareWinner(Team team) throws OperationsException {
        throw new OperationsException();

    }
}
