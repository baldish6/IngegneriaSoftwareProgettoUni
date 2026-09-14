package com.unicam.hackhub.Service;

import com.unicam.hackhub.Error.RichiestaExistException;
import com.unicam.hackhub.Model.Mentore;
import com.unicam.hackhub.Model.Richiesta;
import com.unicam.hackhub.Model.Team;
import com.unicam.hackhub.Repository.RichiestaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class GestoreRichieste {

    private RichiestaRepository richiestaRepository;

    public GestoreRichieste(RichiestaRepository richiestaRepository) {
        this.richiestaRepository = richiestaRepository;
    }

    @Transactional
    public Richiesta addRichiesta(Team team, Mentore mentore,String messaggio){
        if (richiestaRepository.existByTeamAndMentore(team,mentore)){
            throw new RichiestaExistException();
        }
        Richiesta richiesta = new Richiesta(team,mentore,messaggio);
        return richiestaRepository.save(richiesta);
    }
}
