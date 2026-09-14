package com.unicam.hackhub.Repository;

import com.unicam.hackhub.Model.MembroTeam;
import com.unicam.hackhub.Model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembroTeamRepository  extends JpaRepository<MembroTeam,Long> {
    Optional<MembroTeam> findByUtente(Utente utente);
    Boolean existsByUtente(Utente utente);
}
