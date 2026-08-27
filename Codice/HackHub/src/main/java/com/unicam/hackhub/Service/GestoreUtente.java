package com.unicam.hackhub.Service;

import com.unicam.hackhub.Model.Utente;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GestoreUtente {
    private static Map<Integer, Utente> utenteRepository = new HashMap<>();

    public Utente addUtente(Utente utente) {
        utenteRepository.put(utente.getId(), utente);
        return utenteRepository.get(utente.getId());
    }
}
