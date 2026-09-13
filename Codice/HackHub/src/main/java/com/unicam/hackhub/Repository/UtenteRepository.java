package com.unicam.hackhub.Repository;

import com.unicam.hackhub.Model.Utente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends CrudRepository<Utente, Long> {
    Optional<Utente> findByNome(String nome);
    Boolean existsByNome(String nome);
}
