package com.unicam.hackhub.Repository;

import com.unicam.hackhub.Model.MembroTeam;
import com.unicam.hackhub.Model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team,Long> {

    Optional<Team> findByMembroTeam(MembroTeam membroTeam);
}
