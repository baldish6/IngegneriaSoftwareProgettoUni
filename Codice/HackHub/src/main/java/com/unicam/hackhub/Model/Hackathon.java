package com.unicam.hackhub.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
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

    public Hackathon(Integer id, String name, String regolamento, String dataScadenzaIscrizione, String dataInizio, String dataFine, String luogo, Float premio, Giudice giudice, Mentore mentore) {
        this.id = id;
        this.name = name;
        this.regolamento = regolamento;

        DateTimeFormatter dateformatter
                = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        this.dataScadenzaIscrizione =  Objects.requireNonNull(LocalDate.parse(dataScadenzaIscrizione,dateformatter));
        this.dataInizio = Objects.requireNonNull(LocalDate.parse(dataInizio,dateformatter));
        this.dataFine = Objects.requireNonNull(LocalDate.parse(dataFine,dateformatter));

        this.luogo = luogo;
        this.premio = premio;
        this.giudice = giudice;
        this.listMentori.add(mentore);
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
