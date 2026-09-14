package com.unicam.hackhub.Util;

import com.unicam.hackhub.Model.Hackathon;
import com.unicam.hackhub.Model.Mentore;
import com.unicam.hackhub.Model.Team;

import javax.management.OperationsException;

public class Concluso extends HackStato{
    public Concluso(Hackathon hackathon) {
        super(hackathon);
    }

    @Override
    public Boolean canGiveValutazione() throws OperationsException {
        throw  new OperationsException();
    }

    @Override
    public void iscriviHackathon(Team team) throws OperationsException {
        throw  new OperationsException();
    }

    @Override
    public Boolean canChangeSottomissione() throws OperationsException {
        throw  new OperationsException();
    }

    @Override
    public Boolean isActive() throws OperationsException {
        throw  new OperationsException();
    }

    @Override
    public Boolean addMentore(Mentore mentore) throws OperationsException {

        throw  new OperationsException();
    }

    @Override
    public void declareWinner(Team team) throws OperationsException {
        throw  new OperationsException();
    }
}
