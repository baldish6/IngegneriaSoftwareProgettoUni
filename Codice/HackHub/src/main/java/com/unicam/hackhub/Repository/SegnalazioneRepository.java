package com.unicam.hackhub.Repository;

import com.unicam.hackhub.Model.Mentore;
import com.unicam.hackhub.Model.Segnalazione;
import com.unicam.hackhub.Model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SegnalazioneRepository extends JpaRepository<Segnalazione, Long> {
    Boolean existsByTeamAndMentore(Team team, Mentore mentore);

}
