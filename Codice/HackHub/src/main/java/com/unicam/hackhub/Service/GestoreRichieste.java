package com.unicam.hackhub.Service;

import com.unicam.hackhub.Error.RichiestaExistException;
import com.unicam.hackhub.Error.RichiestaNotExistException;
import com.unicam.hackhub.Error.RichiestaNotPrenotataException;
import com.unicam.hackhub.Model.Mentore;
import com.unicam.hackhub.Model.Richiesta;
import com.unicam.hackhub.Model.Team;
import com.unicam.hackhub.Repository.RichiestaRepository;
import com.unicam.hackhub.Util.ExtCalendarAPI;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

@Service
public class GestoreRichieste {

    private RichiestaRepository richiestaRepository;

    public GestoreRichieste(RichiestaRepository richiestaRepository) {
        this.richiestaRepository = richiestaRepository;
    }

    @Transactional
    public Richiesta addRichiesta(Team team, Mentore mentore,String messaggio){



        if (richiestaRepository.existsByTeamAndMentore(team,mentore)){
            throw new RichiestaExistException();
        }
        Richiesta richiesta = new Richiesta(team,mentore,messaggio);
        return richiestaRepository.save(richiesta);
    }

    public Collection<Richiesta> getListRichieste(Mentore mentore){
        return richiestaRepository.findAllByMentore(mentore);
    }

    @Transactional
    public void propostaCall(Long richiestaId, LocalDate date,Mentore mentore){

        Richiesta richiesta = richiestaRepository.findById(richiestaId).orElseThrow(RichiestaNotExistException::new);
        Team team = richiesta.getTeam();
        if (ExtCalendarAPI.prenotaCall(team,date)){
            throw new RichiestaNotPrenotataException();
        }
        team.messageCall(mentore,date);
        richiestaRepository.deleteById(richiestaId);
    }

    @Transactional
    public void teamRemoved(Team team){
        richiestaRepository.deleteAllByTeam(team);
    }


}
