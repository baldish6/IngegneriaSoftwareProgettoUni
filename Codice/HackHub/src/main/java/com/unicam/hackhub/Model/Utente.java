package com.unicam.hackhub.Model;

public class Utente {
    private Integer id;
    private String nome;
    private String password;

    public Utente(Integer id, String nome, String password) {
        this.id = id;
        this.nome = nome;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
