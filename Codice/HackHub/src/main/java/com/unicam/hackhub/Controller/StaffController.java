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

    /*
    private static Map<Integer, Product> productRepository = new HashMap<>();
    static {
        Product miele = new Product(1,"miele",23.67);
        Product zucchero = new Product(2,"zucchero",10.89);

        productRepository.put(miele.getId(), miele);
        productRepository.put(zucchero.getId(), zucchero);
    }

    @RequestMapping(value = "/prod")
    public ResponseEntity<Object> getProducts() {
        return new ResponseEntity<>(productRepository.values(), HttpStatus.OK);
    }*/

    @PostMapping("/addhack")
    //@PreAuthorize("hasAuthority('ADMIN')")
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



    }
