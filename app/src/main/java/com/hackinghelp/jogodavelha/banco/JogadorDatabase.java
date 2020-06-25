package com.hackinghelp.jogodavelha.banco;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.hackinghelp.jogodavelha.model.Jogador;
import com.hackinghelp.jogodavelha.model.JogadorDao;

@Database(entities = {Jogador.class}, version = 2)
public abstract class JogadorDatabase extends RoomDatabase {

    public abstract JogadorDao jogadorDao();

}
