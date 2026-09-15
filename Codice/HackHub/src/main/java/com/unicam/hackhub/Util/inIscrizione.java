package com.unicam.hackhub.Util;

import com.unicam.hackhub.Error.OperationErrException;
import com.unicam.hackhub.Model.Hackathon;
import com.unicam.hackhub.Model.Mentore;
import com.unicam.hackhub.Model.Team;
import jakarta.persistence.Entity;

import javax.management.OperationsException;

@Entity
public class inIscrizione extends HackStato{

    public inIscrizione(Hackathon hackathon) {
        super(hackathon);
    }
    public inIscrizione() {}

    @Override
    public Boolean canGiveValutazione()  {
        throw new OperationErrException();
    }

    @Override
    public void iscriviHackathon(Team team) {
        hackathon.addTeam(team);
    }

    @Override
    public Boolean canChangeSottomissione() {
        throw new OperationErrException();
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
        throw new OperationErrException();
    }
}
