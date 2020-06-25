package com.hackinghelp.jogodavelha.banco;

import android.content.Context;

import androidx.room.Room;

public class Banco {

    private static JogadorDatabase jogadorDatabase;
    private static final Object LOCK = new Object();

    public synchronized static JogadorDatabase getJogadorDatabase(Context context){
        if(jogadorDatabase == null) {
            synchronized (LOCK) {
                if (jogadorDatabase == null) {
                    jogadorDatabase = Room.databaseBuilder(context,
                            JogadorDatabase.class, "jogador BD").fallbackToDestructiveMigration().build();
                }
            }
        }
        return jogadorDatabase;
    }

}
