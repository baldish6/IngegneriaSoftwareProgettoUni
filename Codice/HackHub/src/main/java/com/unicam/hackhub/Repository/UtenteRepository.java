package com.unicam.hackhub.Repository;

import com.unicam.hackhub.Model.Ruolo;
import com.unicam.hackhub.Model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Optional<Utente> findByNome(String nome);
    Boolean existsByNome(String nome);
    Optional<Utente> findByNomeAndRuolo(String nome, Ruolo ruolo);

    @Override
    void deleteAll(Iterable<? extends Utente> entities);
}
