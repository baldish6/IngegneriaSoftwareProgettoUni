package com.unicam.hackhub.Model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Hackathon {
    private Integer id;
    private String name;
    private String regolamento;
    private LocalDate dataScadenzaIscrizione;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private String luogo;
    private Float premio;
    private Giudice giudice;
    private Set<Mentore> listMentori = new HashSet<>();
    private Set<Team> listTeams = new HashSet<>();

    public Hackathon(Integer id, String name, String regolamento, LocalDate dataScadenzaIscrizione, LocalDate dataInizio, LocalDate dataFine, String luogo, Float premio, Giudice giudice, Set<Mentore> listMentori, Set<Team> listTeams) {
        this.id = id;
        this.name = name;
        this.regolamento = regolamento;
        this.dataScadenzaIscrizione = dataScadenzaIscrizione;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.luogo = luogo;
        this.premio = premio;
        this.giudice = giudice;
        this.listMentori = listMentori;
        this.listTeams = listTeams;
    }



    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRegolamento() {
        return regolamento;
    }

    public LocalDate getDataScadenzaIscrizione() {
        return dataScadenzaIscrizione;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public String getLuogo() {
        return luogo;
    }

    public Float getPremio() {
        return premio;
    }

    public Giudice getGiudice() {
        return giudice;
    }

    public Set<Mentore> getListMentori() {
        return listMentori;
    }

    public Set<Team> getListTeams() {
        return listTeams;
    }
}
