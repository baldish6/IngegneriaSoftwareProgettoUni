package com.unicam.hackhub.Service;

import com.unicam.hackhub.Error.UtenteNotInTeamException;
import com.unicam.hackhub.Model.Team;
import com.unicam.hackhub.Model.Utente;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GestoreTeams {
    private static Map<Integer, Team> teamRepository = new HashMap<>();
    private static Map<Integer,Integer> utenteToteam = new HashMap<>();
    private int tot = 0;

    public Team addTeam(Utente utente,String nomeTeam) {
        Team team = new Team(tot,nomeTeam,utente);
        teamRepository.put(tot, team);
        utenteToteam.put(utente.getId(), tot);
        tot++;
        return team;
    }

    public Team getTeam(int id) {
        if (utenteToteam.containsKey(id)) {
            return teamRepository.get(utenteToteam.get(id));
        }
        else throw new UtenteNotInTeamException();

    }
}
