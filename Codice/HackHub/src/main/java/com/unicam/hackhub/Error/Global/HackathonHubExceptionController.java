package com.unicam.hackhub.Error.Global;

import com.unicam.hackhub.Error.HackathonExistException;
import com.unicam.hackhub.Error.HackathonNotExistException;
import com.unicam.hackhub.Error.UtenteExistException;
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




}
