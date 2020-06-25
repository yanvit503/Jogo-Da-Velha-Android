package com.hackinghelp.jogodavelha;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.hackinghelp.jogodavelha.adapter.JogadorAdapter;
import com.hackinghelp.jogodavelha.banco.Banco;
import com.hackinghelp.jogodavelha.model.Jogador;
import com.hackinghelp.jogodavelha.model.JogadorDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    RecyclerView recyclerView;
    JogadorAdapter adapter;
    List<Jogador> jogadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_ranking);

        jogadores = new ArrayList<>();

        //consulta no banco não pode ocorrer na Main Thread
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                JogadorDao dao = Banco.getJogadorDatabase(MainActivity.this).jogadorDao();

                jogadores = dao.getRanking(); //pega ranking

                adapter = new JogadorAdapter(jogadores);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                //caso não haja jogadores ou algum erro, será ignorado

            }
        });

    }

    public void btnJogar(View v)
    {
        startActivity(new Intent(this,Jogo.class));
    }

}
