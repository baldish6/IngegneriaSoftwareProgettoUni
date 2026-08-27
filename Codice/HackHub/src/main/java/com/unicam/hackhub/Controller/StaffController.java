package com.unicam.hackhub.Controller;

import com.unicam.hackhub.Model.Giudice;
import com.unicam.hackhub.Model.Hackathon;
import com.unicam.hackhub.Model.Mentore;
import com.unicam.hackhub.Service.GestoreHackathon;
import com.unicam.hackhub.Service.GestoreUtente;
import com.unicam.hackhub.Util.HackathonInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
        return new ResponseEntity<>(gestoreHackathon.getListHackathon(),HttpStatus.OK);
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



    }
