package com.unicam.hackhub.Repository;

import com.unicam.hackhub.Model.Mentore;
import com.unicam.hackhub.Model.Richiesta;
import com.unicam.hackhub.Model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface RichiestaRepository extends JpaRepository<Richiesta,Long> {
    Boolean existsByTeamAndMentore(Team team, Mentore mentore);
    Collection<Richiesta> findAllByMentore(Mentore mentore);
    void deleteAllByTeam(Team team);
    Optional<Richiesta> findByIdAndMentore(Long id, Mentore mentore);

}
