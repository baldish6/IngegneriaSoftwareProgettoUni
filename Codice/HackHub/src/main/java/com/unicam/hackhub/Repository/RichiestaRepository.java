package com.unicam.hackhub.Repository;

import com.unicam.hackhub.Model.Mentore;
import com.unicam.hackhub.Model.Richiesta;
import com.unicam.hackhub.Model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RichiestaRepository extends JpaRepository<Richiesta,Long> {
    Boolean existByTeamAndMentore(Team team, Mentore mentore);
}
