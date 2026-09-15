package com.unicam.hackhub.Util;

import com.unicam.hackhub.Error.OperationErrException;
import com.unicam.hackhub.Model.Hackathon;
import com.unicam.hackhub.Model.Mentore;
import com.unicam.hackhub.Model.Team;
import jakarta.persistence.Entity;

import javax.management.OperationsException;

@Entity
public class Concluso extends HackStato{
    public Concluso(Hackathon hackathon) {
        super(hackathon);
    }

    public Concluso() {}


    @Override
    public Boolean canGiveValutazione()  {
        throw new OperationErrException();
    }

    @Override
    public void iscriviHackathon(Team team) {
        throw new OperationErrException();
    }

    @Override
    public Boolean canChangeSottomissione() {
        throw new OperationErrException();
    }

    @Override
    public Boolean isActive()  {
        throw new OperationErrException();
    }

    @Override
    public Boolean addMentore(Mentore mentore)  {

        throw new OperationErrException();
    }

    @Override
    public void declareWinner(Team team)  {
        throw new OperationErrException();
    }
}
