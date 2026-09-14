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
    public ResponseEntity<Object> SottNotExist(   SottNotExistException exception){
        return new ResponseEntity<>("la sottomissione non è presente nel database", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NotInTeamException.class)
    public ResponseEntity<Object> NotInTeamPresent(   NotInTeamException exception){
        return new ResponseEntity<>(  "Non sei in nessuna squadra", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TeamNomeExistException.class)
    public ResponseEntity<Object> NomeTeamPresent(  TeamNomeExistException exception){
        return new ResponseEntity<>(  "Esiste già una squadra con questo nome", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UtenteHaveTeamException.class)
    public ResponseEntity<Object> UtenteInTeam(  UtenteHaveTeamException exception){
        return new ResponseEntity<>(  "Sei già in un team", HttpStatus.NOT_FOUND);
    }





}
