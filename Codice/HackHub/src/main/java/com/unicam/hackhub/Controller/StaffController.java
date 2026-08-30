package com.unicam.hackhub.Controller;

import com.unicam.hackhub.Model.*;
import com.unicam.hackhub.Service.GestoreHackathon;
import com.unicam.hackhub.Service.GestoreUtente;
import com.unicam.hackhub.Util.HackathonInfo;
import com.unicam.hackhub.Util.ValutazioneInfo;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


@RestController
@RequestMapping("/staff")
public class StaffController {

    private final GestoreUtente gestoreUtente;
    private final GestoreHackathon gestoreHackathon;

    public StaffController(GestoreUtente gestoreUtente, GestoreHackathon gestoreHackathon) {
        this.gestoreUtente = gestoreUtente;
        this.gestoreHackathon = gestoreHackathon;
    }

    @GetMapping("/hacklist")
    public ResponseEntity<Object> listHackathon(){
        return new ResponseEntity<>(gestoreHackathon.getListHackathon().toString(),HttpStatus.OK);
    }

    @PostMapping("/addhack")
    public ResponseEntity<Object> addHackathon(@RequestBody HackathonInfo hackathon) {

        Giudice giudice = (Giudice) gestoreUtente.addUtente(hackathon.giudice());
        Mentore mentore = (Mentore) gestoreUtente.addUtente(hackathon.mentore());
        Hackathon response = gestoreHackathon.addHackathon(hackathon,giudice,mentore);
        if (response!=null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Hackathon already exist", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addmentore")
    public ResponseEntity<Object> addMentore(
            @RequestParam ("hck") Integer hackathonId , @RequestBody Mentore mentore) {
        Mentore mentore1 = (Mentore) gestoreUtente.addUtente(mentore);
        Boolean resp = gestoreHackathon.addMentore(mentore1, hackathonId);
        if (resp == null) {
            return new ResponseEntity<>("Hackathon does not exist or mentore already present", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Mentore added", HttpStatus.OK);
        }
    }

    @PostMapping("/valuta")
    public ResponseEntity<Object> valuta(
            @RequestParam ("gdc") Integer giudiceId,
            @RequestBody ValutazioneInfo infoVal,
            @RequestParam ("tm") String nomeTeam
    ){
        Giudice giudice = (Giudice) gestoreUtente.getUtente(giudiceId);
        Valutazione valutazione = gestoreHackathon.valuta(giudice,infoVal,nomeTeam);
        if (valutazione!=null){
            return  new ResponseEntity<>("La valutazione è stata aggiunta "+valutazione.toString(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("La valutazione NON è stata aggiunta",HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/sott")
    public ResponseEntity<Object> getSottomissione(
            @RequestParam ("hck") Integer hackathonId,
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

    private ResponseEntity<Object> fileDownload( String filename)
            throws FileNotFoundException
    {
        String path = "src/main/resources/"+filename;
        File file = new File(path);
        InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                String.format("attachment; filename=\"%s\"",file.getName()));
        headers.add("Cache-Control","no-cache, no-store, must-revalidate");
        headers.add("Pragma","no-cache");
        headers.add("Expires","0");

        ResponseEntity<Object> responseEntity = ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt"))
                .body(inputStreamResource);

        return responseEntity;
    }



    }
