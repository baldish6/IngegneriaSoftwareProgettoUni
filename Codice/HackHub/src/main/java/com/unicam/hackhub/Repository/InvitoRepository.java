package com.unicam.hackhub.Repository;

import com.unicam.hackhub.Model.Invito;
import com.unicam.hackhub.Model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvitoRepository extends JpaRepository<Invito,Long> {

    Boolean existsByUtente(Utente utente);

    Optional<Invito> findByUtente(Utente utente);
}
