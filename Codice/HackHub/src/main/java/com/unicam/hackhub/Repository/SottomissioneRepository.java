package com.unicam.hackhub.Repository;

import com.unicam.hackhub.Model.Hackathon;
import com.unicam.hackhub.Model.Sottomissione;
import com.unicam.hackhub.Model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SottomissioneRepository extends JpaRepository<Sottomissione,Long> {
    Boolean existByTeam(Team team);
    Optional<Sottomissione> findByTeam(Team team);
    Optional<Sottomissione> findByHackathonAndTeam_NomeTeam(Hackathon hackathon, String teamNomeTeam);
}
