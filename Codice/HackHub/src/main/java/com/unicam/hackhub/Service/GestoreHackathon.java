package com.unicam.hackhub.Service;

import com.unicam.hackhub.Error.HackathonExistException;
import com.unicam.hackhub.Error.HackathonNotExistException;
import com.unicam.hackhub.Error.SottNotExistException;
import com.unicam.hackhub.Model.*;
import com.unicam.hackhub.Repository.HackathonRepository;
import com.unicam.hackhub.Util.HackathonInfo;
import com.unicam.hackhub.Util.ValutazioneInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GestoreHackathon {

    //private static Map<Integer, Hackathon> hackathonRepository = new HashMap<>();

    private final GestoreSottomissione gestoreSottomissione;

    private final GestoreValutazioni gestoreValutazioni;
    private final HackathonRepository hackathonRepository;


    public GestoreHackathon(GestoreSottomissione gestoreSottomissione, GestoreValutazioni gestoreValutazioni,
                            HackathonRepository hackathonRepository) {
        this.gestoreSottomissione = gestoreSottomissione;
        this.gestoreValutazioni = gestoreValutazioni;
        this.hackathonRepository = hackathonRepository;
    }

    public Hackathon addHackathon(HackathonInfo hackathon, Giudice giudice, Mentore mentore) {
        Hackathon hackathon1 = new Hackathon(
                hackathon.nome(),hackathon.regolamento(),
                hackathon.dataFineIscrizione(),hackathon.dataInizio(),hackathon.dataFine(),
                hackathon.luogo(),hackathon.premio(),hackathon.maxTeam(),giudice,mentore
        );
        /*
        if (!hackathonRepository.containsKey(hackathon.id())) {
            hackathonRepository.put(hackathon.id(), hackathon1);
            return hackathonRepository.get(hackathon.id());
        }
        */
        if (!hackathonRepository.existsByName(hackathon.nome())) {
           return hackathonRepository.save(hackathon1);
        }else {
            throw new HackathonExistException();
        }
    }

    public Collection<Hackathon> getListHackathon(){
        //return hackathonRepository.values();
        return hackathonRepository.findAll();
    }

    public Boolean addMentore(Mentore mentore, Long hackathonId){

        /*

        if (hackathonRepository.containsKey(hackathonId)){
            return hackathonRepository.get(hackathonId)
                    .addMentore(mentore);
        }
        else{
            throw new HackathonNotExistException();
        }*/
        return hackathonRepository.findById(hackathonId)
                .orElseThrow(HackathonNotExistException::new)
                .addMentore(mentore);
    }

    public Collection<Hackathon> getListHackathonLiberi(Team team){
        Integer size = team.getSize();
        /*return hackathonRepository
                .values()
                .stream()
                .filter(x->x.getMaxTeam()>=size).collect(Collectors.toList());
         */
        return hackathonRepository.findLibero(team.getSize());
    }

    public Hackathon iscriviHackathon(Long hackathonId,Team team){
        /*if (hackathonRepository.containsKey(hackathonId)){
            Hackathon hackathon = hackathonRepository.get(hackathonId);
            hackathon.iscriviHackathon(team);
            return hackathon;
        }else {
            throw new HackathonNotExistException();
        }*/
        Hackathon hackathon = hackathonRepository.findById(hackathonId)
                .orElseThrow(HackathonNotExistException::new);
        hackathon.iscriviHackathon(team);
        return hackathon;

    }

    public Sottomissione aggiornaSottomissione(
            Long hackathonId, Team team,
            MultipartFile file, String fileName){

       /* if (hackathonRepository.containsKey(hackathonId)){
            Hackathon hackathon = hackathonRepository.get(hackathonId);
            return gestoreSottomissione.aggiornaSottomissione(hackathon,team,file,fileName);

        }else {
            throw new HackathonNotExistException();
        }*/
        Hackathon hackathon = hackathonRepository.findById(hackathonId).orElseThrow(HackathonNotExistException::new);
        return gestoreSottomissione.aggiornaSottomissione(hackathon,team,file,fileName);
    }

    public Sottomissione getSottomissione(Long hackathonId, String nomeTeam){
        /*if (hackathonRepository.containsKey(hackathonId)){
            Hackathon hackathon = hackathonRepository.get(hackathonId);
            return gestoreSottomissione.getSottomissione(hackathon,nomeTeam);
        }
        else {
            throw new HackathonNotExistException();
        }*/

        Hackathon hackathon = hackathonRepository.findById(hackathonId).orElseThrow(HackathonNotExistException::new);
        return gestoreSottomissione.getSottomissione(hackathon,nomeTeam);


    }

    public void inviaGiudice(Long sottId){
        gestoreSottomissione.InviaGiudice(sottId);
    }

    public Valutazione valuta(Giudice giudice, ValutazioneInfo valutazione,String nomeTeam){

       /* Hackathon hackathon = hackathonRepository
                .values()
                .stream()
                .filter(x->x.getGiudice().equals(giudice))
                .findFirst()
                .orElseThrow(HackathonNotExistException::new);*/

        Hackathon hackathon = hackathonRepository.findByGiudice(giudice)
                .orElseThrow(HackathonNotExistException::new);

        Sottomissione sottomissione = gestoreSottomissione.getSottomissione(hackathon,nomeTeam);
        if (!sottomissione.isInviatoGiudice()){
            throw new SottNotExistException();
        }

       return gestoreValutazioni.addValutazione(valutazione,sottomissione);
    }


}
