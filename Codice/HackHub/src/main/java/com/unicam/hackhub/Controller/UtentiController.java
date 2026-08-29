package com.unicam.hackhub.Controller;

import com.unicam.hackhub.Model.*;
import com.unicam.hackhub.Service.GestoreHackathon;
import com.unicam.hackhub.Service.GestoreTeams;
import com.unicam.hackhub.Service.GestoreUtente;
import com.unicam.hackhub.Util.HackathonInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/usr")
public class UtentiController {

    private final GestoreTeams gestoreTeams;
    private final GestoreUtente gestoreUtente;
    private final GestoreHackathon gestoreHackathon;

    public UtentiController(GestoreTeams gestoreTeams, GestoreUtente gestoreUtente, GestoreHackathon gestoreHackathon) {
        this.gestoreTeams = gestoreTeams;
        this.gestoreUtente = gestoreUtente;
        this.gestoreHackathon = gestoreHackathon;
    }

    @PostMapping("/addteam")
    public ResponseEntity<Object> addTeam(
            @RequestParam ("usr") Integer utenteId, @RequestParam("tm") String nomeTeam) {
       Utente utente = gestoreUtente.getUtente(utenteId);
       Team team = gestoreTeams.addTeam(utente,nomeTeam);
       return new ResponseEntity<>(team.toString(), HttpStatus.OK);
    }

    @GetMapping("/hackliblist")
    public ResponseEntity<Object> listHackathonLiberi( @RequestParam ("usr") Integer utenteId){
        Team team = gestoreTeams.getTeam(utenteId);
        return new ResponseEntity<>(gestoreHackathon.getListHackathonLiberi(team),HttpStatus.OK);
    }

    @PostMapping("/hackiscrivi")
    public ResponseEntity<Object> listHackathonLiberi(
            @RequestParam ("usr") Integer utenteId,
            @RequestParam ("hck") Integer hackathonId){
        Team team = gestoreTeams.getTeam(utenteId);
        Hackathon hackathon = gestoreHackathon.iscriviHackathon(hackathonId,team);
        team.addHackathonIscritti(hackathon);
        return new ResponseEntity<>("iscritto all'hackathon : "+hackathon,HttpStatus.OK);
    }

    @RequestMapping(value = "/aggiorna", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> aggiornaSottomissione(
            @RequestParam("file") MultipartFile file,
            @RequestParam("fileName") String fileName,
            @RequestParam ("usr") Integer userId,
            @RequestParam ("hck") Integer hackathonId
    ){
        gestoreHackathon.aggiornaSottomissione(hackathonId,gestoreTeams.getTeam(userId),file,fileName);
        return new ResponseEntity<>("File aggiornato",HttpStatus.OK);
    }



}
