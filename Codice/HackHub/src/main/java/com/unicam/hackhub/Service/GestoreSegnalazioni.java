package com.unicam.hackhub.Service;

import com.unicam.hackhub.Error.SegnalazioneExistException;
import com.unicam.hackhub.Model.Mentore;
import com.unicam.hackhub.Model.Segnalazione;
import com.unicam.hackhub.Model.Team;
import com.unicam.hackhub.Repository.SegnalazioneRepository;
import org.springframework.stereotype.Service;

@Service
public class GestoreSegnalazioni {

    private SegnalazioneRepository segnalazioneRepository;

    public GestoreSegnalazioni(SegnalazioneRepository segnalazioneRepository) {
        this.segnalazioneRepository = segnalazioneRepository;
    }

    public Segnalazione addSegnalazione(Team team, Mentore mentore,String messaggio) {
        if (!segnalazioneRepository.existsByTeamAndMentore(team, mentore)) {
            Segnalazione segnalazione = new Segnalazione(team,mentore,messaggio);
            return segnalazioneRepository.save(segnalazione);
        }else throw new SegnalazioneExistException();


    }
}
