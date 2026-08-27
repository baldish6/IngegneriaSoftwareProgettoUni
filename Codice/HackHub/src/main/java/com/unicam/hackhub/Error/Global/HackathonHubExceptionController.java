package com.unicam.hackhub.Error.Global;

import com.unicam.hackhub.Error.HackathonExistException;
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
}
