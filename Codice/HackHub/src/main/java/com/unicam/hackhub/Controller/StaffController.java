package com.unicam.hackhub.Controller;

import com.unicam.hackhub.Error.OperationErrException;
import com.unicam.hackhub.Error.SottNotExistException;
import com.unicam.hackhub.Error.TeamNotIscrittoException;
import com.unicam.hackhub.Model.*;
import com.unicam.hackhub.Service.*;
import com.unicam.hackhub.Util.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.management.OperationsException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.unicam.hackhub.Util.FileDownloadUtil.fileDownload;
import static com.unicam.hackhub.Util.GetDateFromString.getLocalDate;


@RestController
@RequestMapping("/staff")
public class StaffController {

    private final GestoreUtente gestoreUtente;
    private final GestoreHackathon gestoreHackathon;
    private final GestoreRichieste gestoreRichieste;
    private final GestoreSegnalazioni gestoreSegnalazioni;
    private GestoreTeam gestoreTeam;
    private ITempo tempo = Tempo.getInstance();

    public StaffController(GestoreUtente gestoreUtente, GestoreHackathon gestoreHackathon,
                           GestoreRichieste gestoreRichieste, GestoreSegnalazioni gestoreSegnalazioni,
                           GestoreTeam gestoreTeam) {
        this.gestoreUtente = gestoreUtente;
        this.gestoreHackathon = gestoreHackathon;
        this.gestoreRichieste = gestoreRichieste;
        this.gestoreSegnalazioni = gestoreSegnalazioni;
        this.gestoreTeam = gestoreTeam;
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private Giudice getGiudice(){
        Authentication authentication = getAuthentication();
        assert authentication != null;
        return (Giudice) authentication.getPrincipal();
    }

    private Mentore getMentore(){
        Authentication authentication = getAuthentication();
        assert authentication != null;
        return (Mentore) authentication.getPrincipal();
    }


    @GetMapping("/hacklist")
    public ResponseEntity<Object> listHackathon(){
        return new ResponseEntity<>(gestoreHackathon.getListHackathon().toString(),HttpStatus.OK);
    }



    /* ------- ADMIN --------------------- */

    @PostMapping("/addhack")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> addHackathon(@RequestBody HackathonInfo hackathon) {
        Giudice giudice = gestoreUtente.addGiudice(hackathon.giudice());
        Mentore mentore = gestoreUtente.addMentore(hackathon.mentore());
        Hackathon response = gestoreHackathon.addHackathon(hackathon,giudice,mentore);
        if (response!=null){
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Hackathon already exist", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addmentore")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> addMentore(
            @RequestParam ("hck") Long hackathonId , @RequestBody UserInfo mentore) {
        Mentore mentore1 =  gestoreUtente.addMentore(mentore);

        Boolean resp = gestoreHackathon.addMentore(mentore1, hackathonId);

        if (resp == null) {
            return new ResponseEntity<>("Hackathon does not exist or mentore already present", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Mentore added", HttpStatus.OK);
        }
    }

    @GetMapping("/sott")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getSottomissione(
            @RequestParam ("hck") Long hackathonId,
            @RequestParam ("tm") String teamNome)
            throws FileNotFoundException {
        Sottomissione sottomissione= gestoreHackathon.getSottomissione(hackathonId,teamNome);
        if (sottomissione!=null){
            return fileDownload(sottomissione.getFilePath());
        }
        else {
            return new ResponseEntity<>(
                    "Sottomissione del team richiesto per quell'hackathon non c'è nel database",
                    HttpStatus.BAD_REQUEST);
        }
    }

    /* ------- BOTH --------------------- */

    @GetMapping("/sottoall")
    @PreAuthorize("hasAuthority('MENTORE') or hasAuthority('GIUDICE')")
    public ResponseEntity<Object> getSottomissioneOther(
           // @RequestParam ("hck") Long hackathonId,
            @RequestParam ("tm") String teamNome)
            throws FileNotFoundException {

        Authentication authentication = getAuthentication();
        assert authentication != null;
        Utente utente = (Utente) authentication.getPrincipal();
        Hackathon hackathon;
        assert utente != null;
        if (utente.getRuolo().equals(Ruolo.GIUDICE)){
            hackathon = gestoreHackathon.getHackathonByGiudice((Giudice) utente);
        }
        else if (utente.getRuolo().equals(Ruolo.MENTORE)){
            hackathon =gestoreHackathon.getHackathonByMentore((Mentore) utente);
        }else throw new SottNotExistException();

        if (!hackathon.partecipa(gestoreTeam.getTeam(teamNome))){
            throw new SottNotExistException();
        }




        Sottomissione sottomissione= gestoreHackathon.getSottomissione(hackathon.getId(),teamNome);
        if (sottomissione!=null){
            return fileDownload(sottomissione.getFilePath());
        }
        else {
            return new ResponseEntity<>(
                    "Sottomissione del team richiesto per quell'hackathon non c'è nel database",
                    HttpStatus.BAD_REQUEST);
        }
    }

    /* ------- GIUDICE --------------------- */


    @PostMapping("/valuta")
    @PreAuthorize("hasAuthority('GIUDICE')")
    public ResponseEntity<Object> valuta(
            //@RequestParam ("gdc") Long giudiceId,
            @RequestBody ValutazioneInfo infoVal,
            @RequestParam ("tm") String nomeTeam
    ){
        //Giudice giudice = (Giudice) gestoreUtente.getUtente(giudiceId);
        Valutazione valutazione = gestoreHackathon.valuta(getGiudice(),infoVal,nomeTeam);
        if (valutazione!=null){
            return  new ResponseEntity<>("La valutazione è stata aggiunta "+valutazione.toString(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("La valutazione NON è stata aggiunta",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listval")
    @PreAuthorize("hasAuthority('GIUDICE')")
    public ResponseEntity<Object> getListaValutazioni(){
        return new ResponseEntity<>(gestoreHackathon.getListaValutazioni(getGiudice()).toString(),HttpStatus.OK);
    }


    /* ------- MENTORE --------------------- */

    @GetMapping("/listrich")
    @PreAuthorize("hasAuthority('MENTORE')")
    public ResponseEntity<Object> getListRichieste(){
        return new ResponseEntity<>(gestoreRichieste.getListRichieste(getMentore()).toString(),HttpStatus.OK);
    }

    @PostMapping("/resprich")
    @PreAuthorize("hasAuthority('MENTORE')")
    public ResponseEntity<Object> propostaCall(
            @RequestParam ("rcst") Long richiestaId,
            @RequestBody  String giorno
    ){
        Mentore mentore = getMentore();

        if(!gestoreHackathon.getHackathonByMentore(mentore).isActive()){
            throw new OperationErrException();
        }

        String g = giorno.substring(1,11);
        gestoreRichieste.propostaCall(richiestaId,GetDateFromString.getLocalDate(g),mentore);
        return new ResponseEntity<>("Appuntamento con team prenotato",HttpStatus.OK);
    }

    @PostMapping("/segnala")
    @PreAuthorize("hasAuthority('MENTORE')")
    public ResponseEntity<Object> segnala(@RequestParam("tm") String nomeTeam,
                                          @RequestBody String messaggio
    ){
        Mentore mentore = getMentore();

        if(!gestoreHackathon.getHackathonByMentore(mentore).isActive()){
            throw new OperationErrException();
        }
        messaggio = messaggio.replaceAll("\"","");
        Segnalazione resp = gestoreSegnalazioni.addSegnalazione(gestoreTeam.getTeam(nomeTeam),mentore,messaggio);
        return new ResponseEntity<>(resp.toString(),HttpStatus.OK);
    }

    @GetMapping("/listsegn")
    @PreAuthorize("hasAuthority('MENTORE')")
    public ResponseEntity<Object> getListSegnalazioni(){
        Collection<Segnalazione> resp = gestoreSegnalazioni.getListeSegnalazioni(getMentore());
        return new ResponseEntity<>(resp.toString(),HttpStatus.OK);
    }

    @DeleteMapping("/delsegn")
    @PreAuthorize("hasAuthority('MENTORE')")
    public ResponseEntity<Object> deleteSegnalazione(@RequestParam("tm") String nomeTeam){
        gestoreSegnalazioni.deleteSegnalazione(getMentore(),nomeTeam);
        return new ResponseEntity<>("Segnalazione eliminata",HttpStatus.OK);
    }

    /* ------- ADMIN --------------------- */

    @GetMapping("/time")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getTime(){
        return new ResponseEntity<>(tempo.getTime(),HttpStatus.OK);
    }

    @PostMapping("/newtime")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> changeTime(
            @RequestParam ("time") String nuovaData

    ){
        LocalDate newDate = getLocalDate(nuovaData);
        tempo.changeTime(newDate);
        return new ResponseEntity<>("La nuova data è : "+tempo.getTime(),HttpStatus.OK);
    }

    @GetMapping("/listallsegn")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getListAllSegnalazioni(){
        List<Segnalazione> resp = gestoreSegnalazioni.getListAllSegnalazioni();
        return new ResponseEntity<>(resp.toString(),HttpStatus.OK);
    }

    @DeleteMapping("/ignora")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> ignoraSegnalazione(@RequestParam("sgn") Long segnalazioneId){
        gestoreSegnalazioni.ignoraSegnalazione(segnalazioneId);
        return new ResponseEntity<>("Segnalazione ignorata",HttpStatus.OK);
    }

    @PostMapping("/avv")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> inviaAvvertimento(@RequestParam("sgn") Long segnalazioneId, @RequestBody String messaggio){
        messaggio = messaggio.replaceAll("\"","");
        gestoreSegnalazioni.inviaAvvertimento(segnalazioneId,messaggio);
        return new ResponseEntity<>("Messaggio di avvertimento inviato",HttpStatus.OK);
    }

    @DeleteMapping("/sospendi")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> sospendiTeam(@RequestParam("sgn") Long segnalazioneId){

        Set<MembroTeam> listUtenti = gestoreSegnalazioni.sospendiTeam(segnalazioneId);
        listUtenti.forEach(x->{
            Utente utente = x.getUtente();
            Team team = gestoreTeam.quitTeam(utente);
            if ( team.isTeamEmpty() ){
                gestoreRichieste.teamRemoved(team);
                gestoreHackathon.teamRemoved(team);
                gestoreTeam.deleteTeam(team);
            }
            gestoreUtente.deleteUtente(utente);
        });

        return new ResponseEntity<>("Team sospeso",HttpStatus.OK);
    }

    @PostMapping("/win")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> declareWinner(@RequestParam("sgn") Long hackathonId,
                                                @RequestParam("tm") String nomeTeam) {
       Team team = gestoreTeam.getTeam(nomeTeam);
       gestoreHackathon.declareWinner(hackathonId,team);
       return new ResponseEntity<>("Vincitore aggiunto",HttpStatus.OK);
    }



    }
