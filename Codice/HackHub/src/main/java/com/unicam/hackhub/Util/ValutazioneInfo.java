package com.unicam.hackhub.Util;

public class ValutazioneInfo {
    private Integer punteggio;
    private String giudizio;

    public ValutazioneInfo(Integer punteggio, String giudizio) {
        if (punteggio>10||punteggio<0){
            throw new IllegalArgumentException("Punteggio invalido");
        }
        this.punteggio = punteggio;
        this.giudizio = giudizio;
    }

    public Integer getPunteggio() {
        return punteggio;
    }

    public String getGiudizio() {
        return giudizio;
    }
}
