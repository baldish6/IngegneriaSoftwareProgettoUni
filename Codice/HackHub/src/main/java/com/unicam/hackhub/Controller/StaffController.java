package com.unicam.hackhub.Controller;

import com.unicam.hackhub.Model.*;
import com.unicam.hackhub.Service.GestoreHackathon;
import com.unicam.hackhub.Service.GestoreUtente;
import com.unicam.hackhub.Util.HackathonInfo;
import com.unicam.hackhub.Util.UserInfo;
import com.unicam.hackhub.Util.ValutazioneInfo;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.unicam.hackhub.Util.FileDownloadUtil.fileDownload;


@RestController
@RequestMapping("/staff")
public class StaffController {

    private final GestoreUtente gestoreUtente;
    private final GestoreHackathon gestoreHackathon;

    public StaffController(GestoreUtente gestoreUtente, GestoreHackathon gestoreHackathon) {
        this.gestoreUtente = gestoreUtente;
        this.gestoreHackathon = gestoreHackathon;
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




    }
