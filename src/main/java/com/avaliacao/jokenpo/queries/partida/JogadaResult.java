package com.avaliacao.jokenpo.queries.partida;

import com.avaliacao.jokenpo.queries.jogador.JogadorResult;

public class JogadaResult {
    private String tipo;      
    private JogadorResult jogador;

    public JogadaResult() {
    }

    public JogadaResult(String tipo, JogadorResult jogador) {
        this.tipo = tipo;
        this.jogador = jogador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public JogadorResult getJogador() {
        return jogador;
    }

    public void setJogador(JogadorResult jogador) {
        this.jogador = jogador;
    }
         
    
}