package com.unicam.hackhub.Repository;


import com.unicam.hackhub.Model.Hackathon;
import com.unicam.hackhub.Model.Sottomissione;
import com.unicam.hackhub.Model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SottomissioneRepository extends JpaRepository<Sottomissione,Long> {
    Optional<Sottomissione> findByTeamAndHackathon(Team team, Hackathon hackathon);
    Optional<Sottomissione> findByHackathonAndTeam_Nome(Hackathon hackathon, String teamNomeTeam);
    void deleteByTeamAndHackathon(Team team, Hackathon hackathon);

    boolean existsByFilePath(String filePath);
}
