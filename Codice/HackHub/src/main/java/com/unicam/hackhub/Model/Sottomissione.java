package com.unicam.hackhub.Model;

import com.unicam.hackhub.Error.SottInvGiudiceException;

import java.util.Objects;

public class Sottomissione {
    private Integer id;
    private Team team;
    private Hackathon hackathon;
    private String filePath;
    private Boolean inviaGiudice= false;

    public Sottomissione(Integer id, Team team, Hackathon hackathon, String filePath) {
        this.id = id;
        this.team = team;
        this.hackathon = hackathon;
        this.filePath = filePath;
    }

    public Integer getId() {
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
