package com.unicam.hackhub.Service;

import com.unicam.hackhub.Auth.JwtUtil;
import com.unicam.hackhub.Error.UtenteExistException;
import com.unicam.hackhub.Error.UtenteNotExistException;
import com.unicam.hackhub.Model.Ruolo;
import com.unicam.hackhub.Model.Utente;
import com.unicam.hackhub.Repository.UtenteRepository;
import com.unicam.hackhub.Util.UserInfo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GestoreUtente {
    //private static Map<Long, Utente> utenteRepository = new HashMap<>();
    private UtenteRepository utenteRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public GestoreUtente(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
                         UtenteRepository utenteRepository) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.utenteRepository = utenteRepository;
    }

    public Utente register(UserInfo input) {
        Utente user = new Utente(
                input.nome(),
                passwordEncoder.encode(input.password()),
                Ruolo.UTENTE);
        return utenteRepository.save(user);
    }

    public Utente login(UserInfo input) {
        Utente user = utenteRepository.findByNome(input.nome())
                .orElseThrow(() -> new RuntimeException("Utente non è nel database"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.nome(),
                        input.password()
                )
        );

        return user;
    }



    public Utente addUtente(Utente utente) {
        /*if (!utenteRepository.containsKey(utente.getId())) {
            utenteRepository.put(utente.getId(), utente);
            return utenteRepository.get(utente.getId());
        }*/
       if (!utenteRepository.existsByNome(utente.getNome())) {
           return utenteRepository.save(utente);
       } else throw new UtenteExistException();
    }

    public Utente getUtente(Long id) {
       /* if (utenteRepository.containsKey(id)) {
            return utenteRepository.get(id);
        }
        else throw new UtenteNotExistException();
        */
      return utenteRepository.findById(id).orElseThrow(UtenteNotExistException::new);



    }

}
