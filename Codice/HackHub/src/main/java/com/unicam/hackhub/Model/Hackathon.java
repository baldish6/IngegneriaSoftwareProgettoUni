package com.unicam.hackhub.Model;

import com.unicam.hackhub.Error.TeamDimensionException;
import com.unicam.hackhub.Error.TeamIscrittoException;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.unicam.hackhub.Util.GetDateFromString.getLocalDate;

@Entity
public class Hackathon {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String regolamento;
    private LocalDate dataScadenzaIscrizione;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private String luogo;
    private Float premio;
    private Integer maxTeam;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Giudice giudice;


    @OneToMany
    private Set<Mentore> listMentori = new HashSet<>();

    @ManyToMany
    private Set<Team> listTeams = new HashSet<>();

    public Hackathon( String name, String regolamento, String dataScadenzaIscrizione, String dataInizio, String dataFine, String luogo, Float premio,Integer maxTeam, Giudice giudice, Mentore mentore) {
        this.name = name;
        this.regolamento = regolamento;



        this.dataScadenzaIscrizione =  getLocalDate(dataScadenzaIscrizione);
        this.dataInizio = getLocalDate(dataInizio);
        this.dataFine = getLocalDate(dataFine);

        this.luogo = luogo;
        this.premio = premio;
        this.maxTeam = maxTeam;
        this.giudice = giudice;
        this.listMentori.add(mentore);
    }

    public Hackathon() {}

    public Boolean addMentore(Mentore mentore){
        return listMentori.add(mentore);
    }

    public void iscriviHackathon(Team team){
        if(listTeams.contains(team)){
            throw new TeamIscrittoException();
        }
        if (team.getSize()>maxTeam){
            throw new TeamDimensionException();
        }
        listTeams.add(team);
    }


    public Long getId() {
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

    public Integer getMaxTeam() {
        return maxTeam;
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

    /*
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Hackathon hackathon = (Hackathon) o;
        return Objects.equals(id, hackathon.id) && Objects.equals(name, hackathon.name) && Objects.equals(regolamento, hackathon.regolamento) && Objects.equals(dataScadenzaIscrizione, hackathon.dataScadenzaIscrizione) && Objects.equals(dataInizio, hackathon.dataInizio) && Objects.equals(dataFine, hackathon.dataFine) && Objects.equals(luogo, hackathon.luogo) && Objects.equals(premio, hackathon.premio) && Objects.equals(maxTeam, hackathon.maxTeam) && Objects.equals(giudice, hackathon.giudice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, regolamento, dataScadenzaIscrizione, dataInizio, dataFine, luogo, premio, maxTeam, giudice);
    }*/

    @Override
    public String toString() {
        return "Hackathon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", regolamento='" + regolamento + '\'' +
                ", dataScadenzaIscrizione=" + dataScadenzaIscrizione +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                ", luogo='" + luogo + '\'' +
                ", premio=" + premio +
                ", maxTeam=" + maxTeam +
                ", giudice=" + giudice +
                ", listMentori=" + listMentori +
                '}';
    }
}
