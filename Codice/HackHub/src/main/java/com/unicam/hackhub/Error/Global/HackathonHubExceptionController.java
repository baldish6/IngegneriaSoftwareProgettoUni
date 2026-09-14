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

    @ExceptionHandler(value = RichiestaExistException.class)
    public ResponseEntity<Object> RichiestaSent(  RichiestaExistException exception){
        return new ResponseEntity<>(  "Richiesta già inviata al mentore", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RichiestaNotExistException.class)
    public ResponseEntity<Object> RichiestaNotFound(  RichiestaNotExistException exception){
        return new ResponseEntity<>(  "Richiesta non trovata nel db", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RichiestaNotPrenotataException.class)
    public ResponseEntity<Object> RichiestaNotPrenotata(  RichiestaNotPrenotataException exception){
        return new ResponseEntity<>(  "Impossibile prenotare la richiesta con il servizio esterno",
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TeamNotExistException.class)
    public ResponseEntity<Object> TeamNotFound(  TeamNotExistException exception){
        return new ResponseEntity<>(  "Il team non è nel database", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = SegnalazioneExistException.class)
    public ResponseEntity<Object> SegnExist(  SegnalazioneExistException exception){
        return new ResponseEntity<>(  "Il team è ià stato segnalato", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = SegnalazioneNotExistException.class)
    public ResponseEntity<Object> SegnNotExist(  SegnalazioneNotExistException exception){
        return new ResponseEntity<>(  "La segnalazione non è nel database", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TeamNotIscrittoException.class)
    public ResponseEntity<Object> TeamNotIscritto(  TeamNotIscrittoException exception){
        return new ResponseEntity<>(  "Questo team non è iscritto a questo hackathon", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = WinnerExistException.class)
    public ResponseEntity<Object> WinnerAlreadyExist(  WinnerExistException exception){
        return new ResponseEntity<>(  "Esiste già un vincitore per questo hackathon", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = PaymentException.class)
    public ResponseEntity<Object> PaymentError(  PaymentException exception){
        return new ResponseEntity<>(  "c'è stato un problema con il sistema di pagamento", HttpStatus.NOT_FOUND);
    }





}
