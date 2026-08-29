package com.unicam.hackhub.Service;

import com.unicam.hackhub.Error.UtenteExistException;
import com.unicam.hackhub.Error.UtenteNotExistException;
import com.unicam.hackhub.Model.Utente;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GestoreUtente {
    private static Map<Integer, Utente> utenteRepository = new HashMap<>();

    static {
        Utente utente = new Utente(1,"mario","password");
        utenteRepository.put(1, utente);
    }

    public Utente addUtente(Utente utente) {
        if (!utenteRepository.containsKey(utente.getId())) {
            utenteRepository.put(utente.getId(), utente);
            return utenteRepository.get(utente.getId());
        }
        else throw new UtenteExistException();
    }

    public Utente getUtente(Integer id) {
        if (utenteRepository.containsKey(id)) {
            return utenteRepository.get(id);
        }else throw new UtenteNotExistException();

    }

}
