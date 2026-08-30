package com.unicam.hackhub.Model;

public class Valutazione {
    private Integer id;
    private Sottomissione sottomissione;
    private Integer punteggio;
    private String giudizio;

    public Valutazione(Integer id, Sottomissione sottomissione, Integer punteggio, String giudizio) {
        this.id = id;
        this.sottomissione = sottomissione;
        if (punteggio>10||punteggio<0){
            throw new IllegalArgumentException("Punteggio invalido");
        }
        this.punteggio = punteggio;
        this.giudizio = giudizio;
    }

    public Integer getId() {
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
