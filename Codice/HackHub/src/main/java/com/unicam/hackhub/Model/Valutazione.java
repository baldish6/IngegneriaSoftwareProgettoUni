package com.unicam.hackhub.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Valutazione {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Sottomissione sottomissione;
    private Integer punteggio;
    private String giudizio;

    public Valutazione(Sottomissione sottomissione, Integer punteggio, String giudizio) {
        this.sottomissione = sottomissione;
        if (punteggio>10||punteggio<0){
            throw new IllegalArgumentException("Punteggio invalido");
        }
        this.punteggio = punteggio;
        this.giudizio = giudizio;
    }

    public Valutazione() {}

    public Long getId() {
        return id;
    }

    public Sottomissione getSottomissione() {
        return sottomissione;
    }

    public Integer getPunteggio() {
        return punteggio;
    }

    public String getGiudizio() {
        return giudizio;
    }

    public void setPunteggio(Integer punteggio) {
        if (punteggio>10||punteggio<0){
            throw new IllegalArgumentException("Punteggio invalido");
        }else {
        this.punteggio = punteggio;}
    }

    public void setGiudizio(String giudizio) {
        this.giudizio = giudizio;
    }

    public void giveResult(Hackathon hackathon){
        Team team = this.sottomissione.getTeam();
        team.giveResult(this,hackathon);
    }



    @Override
    public String toString() {
        return "Valutazione{" +
                "id=" + id +
                ", sottomissione=" + sottomissione +
                ", punteggio=" + punteggio +
                ", giudizio='" + giudizio + '\'' +
                '}';
    }
}
