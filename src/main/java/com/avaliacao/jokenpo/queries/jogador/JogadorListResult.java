package com.avaliacao.jokenpo.queries.jogador;

import java.util.ArrayList;
import java.util.List;

public class JogadorListResult {
    private int qtdTotal;
    private List<JogadorResult> jogadores;

    
    public int getQtdTotal() {
        return qtdTotal;
    }

    public void setQtdTotal(int qtdTotal) {
        this.qtdTotal = qtdTotal;
    }

    public List<JogadorResult> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<JogadorResult> jogadores) {
        this.jogadores = jogadores;
    }

    public JogadorListResult(List<JogadorResult> jogadores) {
        this.jogadores = jogadores;
        this.qtdTotal = jogadores.size();
    }
    public JogadorListResult() {
        this.jogadores = new ArrayList<>();
        this.qtdTotal = 0;
    }
    
}