package com.unicam.hackhub.Service;

import com.unicam.hackhub.Error.NotInTeamException;
import com.unicam.hackhub.Error.TeamNomeExistException;
import com.unicam.hackhub.Error.TeamNotExistException;
import com.unicam.hackhub.Error.UtenteHaveTeamException;
import com.unicam.hackhub.Model.Team;
import com.unicam.hackhub.Model.Utente;
import com.unicam.hackhub.Repository.MembroTeamRepository;
import com.unicam.hackhub.Repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestoreTeam {

    private TeamRepository teamRepository;
    private MembroTeamRepository membroTeamRepository;

    public GestoreTeam(TeamRepository teamRepository,MembroTeamRepository membroTeamRepository) {
        this.teamRepository = teamRepository;
        this.membroTeamRepository = membroTeamRepository;
    }

    @Transactional
    public Team createTeam(String teamName, Utente utente) {

        if (teamRepository.existsByNome(teamName)) {
            throw new TeamNomeExistException();
        }

        if (membroTeamRepository.existsByUtente(utente)) {
            throw new UtenteHaveTeamException();
        }

        Team team = new Team(teamName);
        Team resp = teamRepository.save(team);
        resp.addMembroTeam(utente);
        return resp;
    }


    public Team getTeam(Utente utente) {
        return membroTeamRepository
                .findByUtente(utente)
                .orElseThrow(NotInTeamException::new)
                .getTeam();
    }

    public Team getTeam(String teamName) {
        return teamRepository.findByNome(teamName).orElseThrow(TeamNotExistException::new);
    }

    public List<String> getMessaggi(Utente utente) {
        return getTeam(utente).getMessaggi();
    }




}
