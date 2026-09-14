package com.unicam.hackhub.Repository;

import com.unicam.hackhub.Model.MembroTeam;
import com.unicam.hackhub.Model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends  JpaRepository<Team,Long> {

    //Optional<Team> findByMembroTeam(MembroTeam membroTeam);
    Boolean existsByNome(String nome);
    Optional<Team> findByNome(String nome);
}
