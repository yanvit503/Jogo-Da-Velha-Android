package com.hackinghelp.jogodavelha.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hackinghelp.jogodavelha.R;
import com.hackinghelp.jogodavelha.model.Jogador;

import java.util.List;

public class JogadorAdapter extends RecyclerView.Adapter<JogadorAdapter.JogadorViewHolder> {

    private List<Jogador> jogadores;

    public JogadorAdapter(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    @Override
    public int getItemCount() {
        return jogadores.size();
    }

    @Override
    public JogadorViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_card_jogador, viewGroup, false);
        JogadorViewHolder jvh = new JogadorViewHolder(v);
        return jvh;
    }

    @Override
    public void onBindViewHolder(final JogadorViewHolder jogadorViewHolderViewHolder,final int i) {
        jogadorViewHolderViewHolder.nome.setText(jogadores.get(i).getNome());
        jogadorViewHolderViewHolder.pontuacao.setText(Integer.toString( jogadores.get(i).getPontos()));

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    static class JogadorViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView nome, pontuacao;

        public JogadorViewHolder(View itemView) {

            super(itemView);
            cv = itemView.findViewById(R.id.card);
            nome = itemView.findViewById(R.id.texto_nome_jogador);
            pontuacao = itemView.findViewById(R.id.texto_pontuacao_jogador);

        }

    }
}