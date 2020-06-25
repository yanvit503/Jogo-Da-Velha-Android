package com.hackinghelp.jogodavelha.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Jogador {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nome;
    private int pontos;

    public Jogador(String nome, int pontos) {
        this.nome = nome;
        this.pontos = pontos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
}
