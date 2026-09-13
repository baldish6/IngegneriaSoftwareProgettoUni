package com.unicam.hackhub.Service;

import com.unicam.hackhub.Error.NotInTeamException;
import com.unicam.hackhub.Error.UtenteNotInTeamException;
import com.unicam.hackhub.Model.Team;
import com.unicam.hackhub.Model.Utente;
import com.unicam.hackhub.Repository.MembroTeamRepository;
import com.unicam.hackhub.Repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GestoreTeams {
   /* private static Map<Integer, Team> teamRepository = new HashMap<>();
    private static Map<Long,Integer> utenteToteam = new HashMap<>();
    private int tot = 0;*/

    private TeamRepository teamRepository;
    private MembroTeamRepository membroTeamRepository;

    public Team addTeam(Utente utente,String nomeTeam) {
        Team team = new Team(nomeTeam,utente);
        /*teamRepository.put(tot, team);
        utenteToteam.put(utente.getId(), tot);
        tot++;
        return team;*/
        return teamRepository.save(team);

    }

    /*
    public Team getTeam(Long id) {
        if (utenteToteam.containsKey(id)) {
            return teamRepository.get(utenteToteam.get(id));
        }
        else throw new UtenteNotInTeamException();
    }*/

    public Team getTeam(Utente utente){
        return membroTeamRepository.findByUtente(utente)
                .orElseThrow(NotInTeamException::new)
                .getTeam();
    }
}
