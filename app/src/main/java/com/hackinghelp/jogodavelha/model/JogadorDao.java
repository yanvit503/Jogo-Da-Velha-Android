package com.hackinghelp.jogodavelha.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface JogadorDao {

    @Query("SELECT * FROM jogador ORDER BY pontos DESC")
    List<Jogador> getRanking(); //Retorna lista do ranking

    @Query("SELECT * FROM jogador WHERE nome = :nome")
    Jogador getJogadorByNome(String nome); //Retorna jogador pelo nome

    @Insert
    void guardaJogador(Jogador jogador);

    @Update
    void updateJogador(Jogador jogador);

}
