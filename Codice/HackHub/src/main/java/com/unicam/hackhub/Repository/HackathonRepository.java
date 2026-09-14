package com.unicam.hackhub.Repository;

import com.unicam.hackhub.Model.Giudice;
import com.unicam.hackhub.Model.Hackathon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface HackathonRepository extends JpaRepository<Hackathon,Long> {
    Boolean existsByName(String name);

    @Query(value = "select * from Hackathon where max_team >= ?1 ",nativeQuery = true)
        //@Query(value = "select * from hackathon_list_teams where not list_teams_id = ?2",nativeQuery = true)
    Collection<Hackathon> findLibero(Integer sizeteam);

    Optional<Hackathon> findByGiudice(Giudice giudice);



}
