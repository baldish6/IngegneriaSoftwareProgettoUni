package com.unicam.hackhub.Service;

import com.unicam.hackhub.Error.SegnalazioneExistException;
import com.unicam.hackhub.Error.SegnalazioneNotExistException;
import com.unicam.hackhub.Model.MembroTeam;
import com.unicam.hackhub.Model.Mentore;
import com.unicam.hackhub.Model.Segnalazione;
import com.unicam.hackhub.Model.Team;
import com.unicam.hackhub.Repository.SegnalazioneRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class GestoreSegnalazioni {

    private SegnalazioneRepository segnalazioneRepository;

    public GestoreSegnalazioni(SegnalazioneRepository segnalazioneRepository) {
        this.segnalazioneRepository = segnalazioneRepository;
    }

    @Transactional
    public Segnalazione addSegnalazione(Team team, Mentore mentore,String messaggio) {
        if (!segnalazioneRepository.existsByTeamAndMentore(team, mentore)) {
            Segnalazione segnalazione = new Segnalazione(team,mentore,messaggio);
            return segnalazioneRepository.save(segnalazione);
        }else throw new SegnalazioneExistException();
    }

    public Collection<Segnalazione> getListeSegnalazioni(Mentore mentore) {
        return segnalazioneRepository.findAllByMentore(mentore);
    }

    @Transactional
    public void deleteSegnalazione(Mentore mentore,String nomeTeam) {
        segnalazioneRepository.deleteByMentoreAndTeam_Nome(mentore,nomeTeam);
    }

    public List<Segnalazione> getListAllSegnalazioni(){
        return segnalazioneRepository.findAll();
    }

    @Transactional
    public void ignoraSegnalazione(Long segnalazioneId) {
        segnalazioneRepository.deleteById(segnalazioneId);
    }

    @Transactional
    public void inviaAvvertimento(Long segnalazioneId, String messaggio) {
        segnalazioneRepository
                .findById(segnalazioneId)
                .orElseThrow(SegnalazioneNotExistException::new)
                .inviaAvvertimento(messaggio);
    }

    @Transactional
    public Set<MembroTeam> sospendiTeam(Long segnalazioneId) {
        return segnalazioneRepository
                .findById(segnalazioneId)
                .orElseThrow(SegnalazioneNotExistException::new)
                .sospendiTeam();

    }

}
