package com.unicam.hackhub.Model;

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

    public Boolean getInviaGiudice() {
        return inviaGiudice;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
