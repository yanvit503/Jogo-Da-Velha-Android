package com.hackinghelp.jogodavelha;

import androidx.appcompat.app.AlertDialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hackinghelp.jogodavelha.banco.Banco;
import com.hackinghelp.jogodavelha.model.Jogador;
import com.hackinghelp.jogodavelha.model.JogadorDao;

import java.util.ArrayList;

public class Jogo extends Activity {

    String nome1,nome2;
    int[] casas;
    TextView tituloTurno;
    ArrayList<Button> botoes;
    boolean turno = true;//se turno = true, vez do jogador X


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);
        tituloTurno = findViewById(R.id.text_titulo_turno);

        botoes = new ArrayList<>();
        casas = new int[9];

        criaDialogo();
        preencheBotao();
    }


    void criaDialogo()
    {

        final AlertDialog.Builder alert = new AlertDialog.Builder(Jogo.this);
        final AlertDialog alertDialog = alert.setView(R.layout.alert_nome_jogadores).create();

        alertDialog.setCancelable(false);
        alertDialog.show();

        Button btn = alertDialog.findViewById(R.id.btn_confirmar);

        final EditText txtNome1 = alertDialog.findViewById(R.id.texto_nome1);
        final EditText txtNome2 = alertDialog.findViewById(R.id.texto_nome2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nome1 = txtNome1.getText().toString();
                nome2 = txtNome2.getText().toString();

                tituloTurno.setText("Vez do jogador " + nome1);

                alertDialog.cancel();
                alertDialog.dismiss();

            }
        });

    }


    void preencheBotao()
    {

        for (int i = 0; i <= 8;i++)
        {
            int id = getResources().getIdentifier("button"+ (i +1) , "id", getPackageName());
            final Button btn = findViewById(id);

            casas[i] = (i + 1) * 10; //preenche as casas com um valor qualquer para não serem iguais antes de jogar

            final int finalI = i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //se turno = true, vez do jogador X

                    if (turno) {
                        btn.setText("X");
                        casas[finalI] = 0; //valor zero,jogador X
                        tituloTurno.setText("Vez do jogador " + nome2);
                        verificaCasas(0);

                    } else {
                        btn.setText("O");
                        casas[finalI] = 1;
                        tituloTurno.setText("Vez do jogador " + nome1);
                        verificaCasas(1);
                    }

                    turno = !turno;
                    btn.setEnabled(false);
                }
            });

            botoes.add(btn);

        }
    }

    void verificaCasas(int indiceJogador)
    {

        if(
            casas[0] == casas[1] && casas[1] == casas[2] ||
            casas[3] == casas[4] && casas[4] == casas[5] ||
            casas[6] == casas[7] && casas[7] == casas[8] || //verificação horizontal

            casas[0] == casas[3] && casas[3] == casas[6] ||
            casas[1] == casas[4] && casas[4] == casas[7] ||
            casas[2] == casas[5] && casas[5] == casas[8] || //verificação vertical

            casas[2] == casas[4] && casas[4] == casas[6] ||
            casas[0] == casas[4] && casas[4] == casas[8]    //verificação diagonal
          )
        {
            for(Button b : botoes) //desativa todos os botões ao ganhar
            {
                b.setEnabled(false);
            }

            if(indiceJogador == 0)
            {
                dialogoGanhador("Jogador " + nome1 +" ganhou !",nome1);

            }
            else
            {
                dialogoGanhador("Jogador " + nome2 +" ganhou !",nome2);
            }
        }

    }


    void dialogoGanhador(String titulo, final String ganhador)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(Jogo.this).create();
        alertDialog.setMessage(titulo);
        alertDialog.setCancelable(false);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {

                            JogadorDao dao = Banco.getJogadorDatabase(Jogo.this).jogadorDao();

                            Jogador vencedor = dao.getJogadorByNome(ganhador);

                            if(vencedor == null) //verifica se o jogador existe, caso sim faça update
                            {
                                dao.guardaJogador(new Jogador(ganhador,1));
                            }
                            else
                            {
                                vencedor.setPontos(vencedor.getPontos() + 1); //add o ponto
                                dao.updateJogador(vencedor);//atualiza
                            }

                            startActivity(new Intent(Jogo.this,MainActivity.class));

                        }
                    });
                }
            });
        alertDialog.show();
    }
}
