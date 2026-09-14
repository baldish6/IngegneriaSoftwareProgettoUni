package com.unicam.hackhub.Controller;

import com.unicam.hackhub.Model.*;
import com.unicam.hackhub.Service.GestoreHackathon;
import com.unicam.hackhub.Service.GestoreTeam;
import com.unicam.hackhub.Service.GestoreUtente;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@RestController
@RequestMapping("/usr")
public class UtentiController {

    private final GestoreTeam gestoreTeam;
    private final GestoreUtente gestoreUtente;
    private final GestoreHackathon gestoreHackathon;

    public UtentiController(GestoreTeam  gestoreTeam, GestoreUtente gestoreUtente, GestoreHackathon gestoreHackathon) {
        this.gestoreTeam = gestoreTeam;
        this.gestoreUtente = gestoreUtente;
        this.gestoreHackathon = gestoreHackathon;
    }

    private Utente getUtenteId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        return (Utente) authentication.getPrincipal();

    }

    @PostMapping("/addteam")
    @PreAuthorize("hasAuthority('UTENTE')")
    public ResponseEntity<Object> addTeam(
           // @RequestParam ("usr") Long utenteId,
            @RequestParam("tm") String nomeTeam) {
      // Utente utente = gestoreUtente.getUtente(utenteId);
        Utente utente = getUtenteId();
       Team team = gestoreTeam.createTeam(nomeTeam, utente);
       return new ResponseEntity<>(team.toString(), HttpStatus.OK);
    }

    @GetMapping("/hackliblist")
    @PreAuthorize("hasAuthority('UTENTE')")
    public ResponseEntity<Object> listHackathonLiberi( ){
        Team team = gestoreTeam.getTeam(getUtenteId());
        return new ResponseEntity<>(gestoreHackathon.getListHackathonLiberi(team).toString(),HttpStatus.OK);
    }

    @PostMapping("/hackiscrivi")
    @PreAuthorize("hasAuthority('UTENTE')")
    public ResponseEntity<Object> listHackathonLiberi(
           // @RequestParam ("usr") Integer utenteId,
            @RequestParam ("hck") Long hackathonId){
        Team team = gestoreTeam.getTeam(getUtenteId());
        Hackathon hackathon = gestoreHackathon.iscriviHackathon(hackathonId,team);
       team.addHackathonIscritti(hackathon);
        return new ResponseEntity<>("iscritto all'hackathon : "+hackathon,HttpStatus.OK);
    }




    @RequestMapping(value = "/aggiorna", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('UTENTE')")
    public ResponseEntity<Object> aggiornaSottomissione(
            @RequestParam("file") MultipartFile file,
            @RequestParam("fileName") String fileName,
            //@RequestParam ("usr") Integer userId,
            @RequestParam ("hck") Long hackathonId
    ){
        gestoreHackathon.aggiornaSottomissione(hackathonId,gestoreTeam.getTeam(getUtenteId()),file,fileName);
        return new ResponseEntity<>("File aggiornato",HttpStatus.OK);
    }

    @PostMapping("/invgiud")
    @PreAuthorize("hasAuthority('UTENTE')")
    public ResponseEntity<Object> inviaGiudiceSottomissione(
            @RequestParam ("sid") Long sottId){
        gestoreHackathon.inviaGiudice(sottId);
        return new ResponseEntity<>("Sottomissione inviata",HttpStatus.OK);
    }



}
