package com.unicam.hackhub.Util;

import com.unicam.hackhub.Model.Giudice;
import com.unicam.hackhub.Model.Mentore;
import com.unicam.hackhub.Model.Utente;

public record HackathonInfo(
    Integer id,
    String nome,
    String regolamento,
    String dataFineIscrizione,
    String dataInizio,
    String dataFine,
    String luogo,
    Float premio,
    Integer maxTeam,
    Giudice giudice,
    Mentore mentore
) {}
