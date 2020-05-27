package com.avaliacao.jokenpo.domain;

public class Jogada{

    private String tipo;
    private Jogador jogador;

    public Jogada() {}
    
    public Jogada(String tipo, Jogador jogador) {
        this.tipo = tipo;
        this.jogador = jogador;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    

    
}