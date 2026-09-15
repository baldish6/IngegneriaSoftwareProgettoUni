package com.unicam.hackhub.Model;

import com.unicam.hackhub.Error.TeamDimensionException;
import com.unicam.hackhub.Error.TeamIscrittoException;
import com.unicam.hackhub.Error.TeamNotIscrittoException;
import com.unicam.hackhub.Error.WinnerExistException;
import com.unicam.hackhub.Util.*;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import javax.management.OperationsException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static com.unicam.hackhub.Util.GetDateFromString.getLocalDate;

@Entity
public class Hackathon {// implements ITimeListener  {

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
    private Boolean endValutazione=false;
    /*@ManyToOne
    private Team winnerTeam;*/


    @OneToOne(cascade = CascadeType.ALL)
    private HackStato stato;


    @OneToOne(cascade = CascadeType.REMOVE)
    private Giudice giudice;


    @OneToMany
    private Set<Mentore> listMentori = new HashSet<>();

    @ManyToMany
    private Set<Team> listTeams = new HashSet<>();

    /*


     hackathon.nome(),hackathon.regolamento(),
                hackathon.dataFineIscrizione(),hackathon.dataInizio(),hackathon.dataFine(),
                hackathon.luogo(),hackathon.premio(),hackathon.maxTeam(),giudice,mentore
     */

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
        //this.winnerTeam = null;
      this.stato=null;
    }

    public Hackathon() {}

    public Boolean changeMentoreList(Mentore mentore) {
       return listMentori.add(mentore);

    }

    public Boolean addMentore(Mentore mentore) {
        return stato.addMentore(mentore);
        //return changeMentoreList(mentore);
    }

    public void addTeam(Team team) {
        if(listTeams.contains(team)){
            throw new TeamIscrittoException();
        }
        if (team.getSize()>maxTeam){
            throw new TeamDimensionException();
        }
        listTeams.add(team);

    }

    public void iscriviHackathon(Team team) {
        stato.iscriviHackathon(team);
        //addTeam(team);
    }

    public void teamRemoved(Team team){
        listTeams.remove(team);
    }

    public Boolean partecipa(Team team){
        return listTeams.contains(team);
    }

    public void declareWinner(Team team) {
        stato.declareWinner(team);
        //addWinner(team);

    }

    public void addWinner(Team team){
        changeState(new Concluso(this));
    }

    /*
    public void addWinner(Team team){
        if (!listTeams.contains(team)){
            throw new TeamNotIscrittoException();
        }
        if (winnerTeam!=null){
            throw new WinnerExistException();
        }
        winnerTeam = team;
       // changeState(new Concluso(this));
    }*/

    public Boolean canGiveValutazione() {
        return  stato.canGiveValutazione();
        //return true;
    }

    public Boolean canChangeSottomissione() {
        return  stato.canChangeSottomissione();
        //return true;
    }

    public Boolean isActive()  {
       return stato.isActive();
        //return true;
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

    public Boolean getEndValutazione() {
        return endValutazione;
    }

    public void setEndValutazione() {
        this.endValutazione = true;
    }

    /*
    public Team getWinnerTeam() {
        return winnerTeam;
    }*/


   public HackStato getStato() {
        return stato;
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

    /*@Override
    public void update(LocalDate time) {

    }*/
    @Transactional
    public void changeState(HackStato state) {
        this.stato = state;
    }

    //@Override
    @Transactional
    public void update(LocalDate time) {

        if (dataFine.isBefore(time)) {
            changeState(new inValutazione(this));
        }
        else if (dataInizio.isBefore(time)) {
            changeState(new inCorso(this));
        }

        else if (dataScadenzaIscrizione.isBefore(time)) {
            changeState(new inCorso(this));
        }
        else if (dataScadenzaIscrizione.isAfter(time)) {
            this.stato=new inIscrizione(this);
        }

        else if (this.stato==null) {
            changeState(new inIscrizione(this));
        }
    }
}
