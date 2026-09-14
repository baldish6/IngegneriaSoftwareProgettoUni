package com.unicam.hackhub.Service;

import com.unicam.hackhub.Model.Hackathon;
import com.unicam.hackhub.Model.Sottomissione;
import com.unicam.hackhub.Model.Team;
import com.unicam.hackhub.Model.Valutazione;
import com.unicam.hackhub.Repository.ValutazioneRepository;
import com.unicam.hackhub.Util.ValutazioneInfo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class GestoreValutazioni {
    /*private static Map<Integer, Valutazione> valutazioneRepository = new HashMap<>();
    private static Integer tot = 0;*/
    private ValutazioneRepository valutazioneRepository;

    public GestoreValutazioni(ValutazioneRepository valutazioneRepository) {
        this.valutazioneRepository = valutazioneRepository;
    }


    @Transactional
    public Valutazione addValutazione(ValutazioneInfo valutazioneInfo, Sottomissione sottomissione) {

        /*
        Optional<Valutazione> optVal = valutazioneRepository
                .values()
                .stream()
                .filter(x->x.getSottomissione().equals(sottomissione))
                .findFirst();*/
        Optional<Valutazione> optVal = valutazioneRepository.findBySottomissione(sottomissione);


        if (optVal.isPresent()) {
            Valutazione valutazione = optVal.get();
            valutazione.setGiudizio(valutazioneInfo.getGiudizio());
            valutazione.setPunteggio(valutazioneInfo.getPunteggio());
            return valutazione;
        }
        else {
            Valutazione valutazione = new Valutazione(
                   sottomissione,valutazioneInfo.getPunteggio(),valutazioneInfo.getGiudizio());
            //valutazioneRepository.put(tot, valutazione);
            //tot++;
            return valutazioneRepository.save(valutazione);
        }
    }

    public Collection<Valutazione> getListaValutazioni(Hackathon hackathon) {
        return this.valutazioneRepository.findAllBySottomissione_Hackathon(hackathon);
    }

    @Transactional
    public void deleteValutazione(Hackathon hackathon, Team team) {
        this.valutazioneRepository.deleteBySottomissione_HackathonAndSottomissione_Team(hackathon, team);
    }

    @Transactional
    public void giveResult(Hackathon hackathon) {

        valutazioneRepository
                .findAllBySottomissione_Hackathon(hackathon)
                .forEach(x->x.giveResult(hackathon));

    }

}
