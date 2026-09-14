package com.unicam.hackhub.Repository;

import com.unicam.hackhub.Model.Mentore;
import com.unicam.hackhub.Model.Segnalazione;
import com.unicam.hackhub.Model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface SegnalazioneRepository extends JpaRepository<Segnalazione, Long> {
    Boolean existsByTeamAndMentore(Team team, Mentore mentore);

    Collection<Segnalazione> findAllByMentore(Mentore mentore);

    void deleteByMentoreAndTeam_Nome(Mentore mentore,String nome);

}
