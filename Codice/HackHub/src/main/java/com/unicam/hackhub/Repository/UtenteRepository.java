package com.unicam.hackhub.Repository;

import com.unicam.hackhub.Model.Ruolo;
import com.unicam.hackhub.Model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Optional<Utente> findByNome(String nome);
    Boolean existsByNome(String nome);
    Optional<Utente> findByNomeAndRuolo(String nome, Ruolo ruolo);

    @Query(
            value = "select nome from utente" +
                    " where ruolo='UTENTE' " +
                    "and id in " +
                    "(select id from utente except select utente_id from membro_team)",
            nativeQuery = true
    )
    Collection<String> findaAllUtentiLiberi();

    @Override
    void deleteAll(Iterable<? extends Utente> entities);
}
