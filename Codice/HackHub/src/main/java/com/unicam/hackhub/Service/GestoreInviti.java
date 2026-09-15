package com.unicam.hackhub.Service;

import com.unicam.hackhub.Error.InvitoNotExistException;
import com.unicam.hackhub.Model.Invito;
import com.unicam.hackhub.Model.Team;
import com.unicam.hackhub.Model.Utente;
import com.unicam.hackhub.Repository.InvitoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class GestoreInviti {

    private InvitoRepository invitoRepository;

    public GestoreInviti(InvitoRepository invitoRepository) {
        this.invitoRepository = invitoRepository;
    }


    @Transactional
    public void addInvito(Team team, Utente utente){
        Optional<Invito> optInvito = invitoRepository.findByUtente(utente);
        if (optInvito.isPresent()){
            optInvito.get().addTeam(team);
        }else {
            Invito invito = new Invito(team,utente);
            invitoRepository.save(invito);
        }
    }

    public Set<Team> getListInviti(Utente utente){
        Optional<Invito> invito = invitoRepository.findByUtente(utente);
        if (invito.isPresent()){
            return invito.get().getListTeams();
        }
        else {
            return Collections.emptySet();
        }
    }

    @Transactional
    public void accettaInvito(Team team, Utente utente){
        invitoRepository.findByUtente(utente)
                .orElseThrow(InvitoNotExistException::new)
                .accettaInvito(team);
        invitoRepository.deleteByUtente(utente);
    }
}
