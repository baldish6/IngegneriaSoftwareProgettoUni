package com.unicam.hackhub.Repository;

import com.unicam.hackhub.Model.Sottomissione;
import com.unicam.hackhub.Model.Valutazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ValutazioneRepository extends JpaRepository<Valutazione,Long> {

    Optional<Valutazione> findBySottomissione(Sottomissione sottomissione);
}
