package com.unicam.hackhub.Service;

import com.unicam.hackhub.Error.*;
import com.unicam.hackhub.Model.*;
import com.unicam.hackhub.Repository.HackathonRepository;
import com.unicam.hackhub.Util.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.management.OperationsException;
import java.time.LocalDate;
import java.util.Collection;

@Service
public class GestoreHackathon implements ITimeListener {

    //private static Map<Integer, Hackathon> hackathonRepository = new HashMap<>();

    private  GestoreSottomissione gestoreSottomissione;
    private GestoreValutazioni gestoreValutazioni;
    private  HackathonRepository hackathonRepository;
    private ITempo tempo = Tempo.getInstance();


    public GestoreHackathon(GestoreSottomissione gestoreSottomissione, GestoreValutazioni gestoreValutazioni,
                            HackathonRepository hackathonRepository) {
        this.gestoreSottomissione = gestoreSottomissione;
        this.gestoreValutazioni = gestoreValutazioni;
        this.hackathonRepository = hackathonRepository;
        tempo.subscribe(this);
    }

    @Transactional
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
             Hackathon resp =  hackathonRepository.save(hackathon1);

           //tempo.subscribe(resp);

           resp.update(tempo.getTime());


           return resp;
        }else {
            throw new HackathonExistException();
        }
    }

    @Transactional
    public Collection<Hackathon> getListHackathon(){

        return hackathonRepository.findAll();
    }

    public Hackathon getHackathonByGiudice(Giudice giudice) {
        return hackathonRepository.findByGiudice(giudice).orElseThrow(HackathonNotExistException::new);
    }

    public Hackathon getHackathonByMentore(Mentore mentore) {
        return hackathonRepository.findByMentore(mentore.getId()).orElseThrow(HackathonNotExistException::new);
    }

    @Transactional
    public Boolean addMentore(Mentore mentore, Long hackathonId) {

        /*

        if (hackathonRepository.containsKey(hackathonId)){
            return hackathonRepository.get(hackathonId)
                    .addMentore(mentore);
        }
        else{
            throw new HackathonNotExistException();
        }*/

        return hackathonRepository
                .findById(hackathonId)
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

        Collection<Hackathon> resp = hackathonRepository.findLibero(size);

        return resp;
    }

    @Transactional
    public Hackathon iscriviHackathon(Long hackathonId,Team team)  {
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

    @Transactional
    public Sottomissione aggiornaSottomissione(
            Long hackathonId, Team team,
            MultipartFile file, String fileName) {

       /* if (hackathonRepository.containsKey(hackathonId)){
            Hackathon hackathon = hackathonRepository.get(hackathonId);
            return gestoreSottomissione.aggiornaSottomissione(hackathon,team,file,fileName);

        }else {
            throw new HackathonNotExistException();
        }*/

        Hackathon hackathon = hackathonRepository.findById(hackathonId).orElseThrow(HackathonNotExistException::new);
        if (!hackathon.canChangeSottomissione()){
            throw new OperationErrException();
        }
        if (!hackathon.partecipa(team)){
            throw new TeamNotIscrittoException();
        }
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

    @Transactional
    public void inviaGiudice(Long sottId,Team team){
        gestoreSottomissione.InviaGiudice(sottId,team);
    }

    @Transactional
    public Valutazione valuta(Giudice giudice, ValutazioneInfo valutazione,String nomeTeam){

       /* Hackathon hackathon = hackathonRepository
                .values()
                .stream()
                .filter(x->x.getGiudice().equals(giudice))
                .findFirst()
                .orElseThrow(HackathonNotExistException::new);*/

        Hackathon hackathon = hackathonRepository.findByGiudice(giudice)
                .orElseThrow(HackathonNotExistException::new);

        if (!hackathon.canGiveValutazione()){
            throw new OperationErrException();
        }

        Sottomissione sottomissione = gestoreSottomissione.getSottomissione(hackathon,nomeTeam);
        if (!sottomissione.isInviatoGiudice()){
            throw new SottNotExistException();
        }

       return gestoreValutazioni.addValutazione(valutazione,sottomissione);
    }

    public Boolean isActive(Team team,Mentore mentore){
        // check if team in mentore
        // check if hackathon active
        return Boolean.TRUE;
    }

    @Transactional
    public void deleteSottomissione(Long hackathonId,Team team) {
        Hackathon hackathon = hackathonRepository.findById(hackathonId).orElseThrow(HackathonNotExistException::new);
        if (!hackathon.canChangeSottomissione()){
           throw new OperationErrException();
        }
        gestoreSottomissione.deleteSottomissione(team,hackathon);
    }


    public Sottomissione getSottomissione(Long hackathonId,Team team){
        Hackathon hackathon = hackathonRepository.findById(hackathonId).orElseThrow(HackathonNotExistException::new);
        return  gestoreSottomissione.getSottomissione(team,hackathon);
    }

    public Collection<Valutazione> getListaValutazioni(Giudice giudice){
        Hackathon hackathon = hackathonRepository.findByGiudice(giudice).orElseThrow(HackathonNotExistException::new);
        return gestoreValutazioni.getListaValutazioni(hackathon);
    }

    @Transactional
    public void teamRemoved(Team team){
        team.getHackathonsIscritti().forEach(x->{
            x.teamRemoved(team);
            gestoreValutazioni.deleteValutazione(x,team);
            gestoreSottomissione.deleteSottomissione(team,x);
        });
    }

    @Transactional
    public void declareWinner(Long hackathonId,Team team) {
        Hackathon hackathon = hackathonRepository.findById(hackathonId).orElseThrow(HackathonNotExistException::new);
        hackathon.declareWinner(team);
        Float premio = hackathon.getPremio();
        Boolean resp = ExtPaymentApi.givePremio(team,premio);
        if (!resp){
            throw new PaymentException();
        }

        gestoreValutazioni.giveResult(hackathon);

       //tempo.unsubscribe(hackathon);

    }

    @Override
    @Transactional
    public void update(LocalDate time) {
        Collection<Hackathon> resp = hackathonRepository.findAttivi();
        resp.forEach(hackathon -> {hackathon.update(time);});
    }

    /*
    @Override
    public void update(LocalDate time) {
        //todo
       // hackathonRepository.findActiveHackathons().forEach(hackathon -> {hackathon.update(time);});
    }*/
}
