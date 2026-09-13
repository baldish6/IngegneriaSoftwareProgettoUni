package com.unicam.hackhub.Model;

import com.unicam.hackhub.Error.SottInvGiudiceException;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Sottomissione {


    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Team team;
    @ManyToOne
    private Hackathon hackathon;
    private String filePath;
    private Boolean inviaGiudice= false;

    public Sottomissione( Team team, Hackathon hackathon, String filePath) {

        this.team = team;
        this.hackathon = hackathon;
        this.filePath = filePath;
    }

    public Sottomissione() {}

    public Long getId() {
        return id;
    }

    public Team getTeam() {
        return team;
    }

    public Hackathon getHackathon() {
        return hackathon;
    }

    public Boolean isInviatoGiudice() {
        return inviaGiudice;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void inviaGiudice() {
        if (inviaGiudice){
            throw new SottInvGiudiceException();
        }
        inviaGiudice = true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Sottomissione that = (Sottomissione) o;
        return Objects.equals(id, that.id) && Objects.equals(team, that.team) && Objects.equals(hackathon, that.hackathon) && Objects.equals(filePath, that.filePath) && Objects.equals(inviaGiudice, that.inviaGiudice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, team, hackathon, filePath, inviaGiudice);
    }

    @Override
    public String toString() {
        return "Sottomissione{" +
                "id=" + id +
                ", team=" + team +
                ", hackathon=" + hackathon +
                ", filePath='" + filePath + '\'' +
                ", inviaGiudice=" + inviaGiudice +
                '}';
    }
}
