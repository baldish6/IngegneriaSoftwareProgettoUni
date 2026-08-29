package com.unicam.hackhub.Error.Global;

import com.unicam.hackhub.Error.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HackathonHubExceptionController {

    @ExceptionHandler(value = HackathonExistException.class)
    public ResponseEntity<Object> HackAlreadyExist(HackathonExistException exception){
        return new ResponseEntity<>("Hackathon già esiste nel database", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = HackathonNotExistException.class)
    public ResponseEntity<Object> HackNotExist(HackathonNotExistException exception){
        return new ResponseEntity<>("Hackathon NON esiste nel database", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UtenteExistException.class)
    public ResponseEntity<Object> UtenteExist(UtenteExistException exception){
        return new ResponseEntity<>("Utente già esiste nel database", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UtenteNotExistException.class)
    public ResponseEntity<Object> UtenteNotExist(UtenteNotExistException exception){
        return new ResponseEntity<>("Utente NON esiste nel database", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TeamIscrittoException.class)
    public ResponseEntity<Object> TeamIscritto( TeamIscrittoException exception){
        return new ResponseEntity<>("Il tuo team è già iscritto a questo hackathon", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TeamDimensionException.class)
    public ResponseEntity<Object> TeamDim( TeamDimensionException exception){
        return new ResponseEntity<>("Il tuo team è troppo grande per questo hackathon", HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = UtenteNotInTeamException.class)
    public ResponseEntity<Object> UtenteNotInTeam( UtenteNotInTeamException exception){
        return new ResponseEntity<>("l'utente non è membro di un team", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = SottInvGiudiceException.class)
    public ResponseEntity<Object> SottInviata(  SottInvGiudiceException exception){
        return new ResponseEntity<>("la sottomissione è già stata inviata al giudice", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = SottNotExistException.class)
    public ResponseEntity<Object> SottNotExisy(   SottNotExistException exception){
        return new ResponseEntity<>("la sottomissione non è presente nel database", HttpStatus.NOT_FOUND);
    }



}
