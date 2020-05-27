package com.avaliacao.jokenpo.domain.partida;

import java.util.ArrayList;
import java.util.List;

import com.avaliacao.jokenpo.domain.Jogada;
import com.avaliacao.jokenpo.domain.Jogador;

public class State {
    
    public static final String RESULTADO_EMPATE = "EMPATE!";
    public static final String RESULTADO_VITORIA = "VITÓRIA!";
    
    public final static String TIPO_JOGADA = "NENHUMA";
    
    private List<Jogada> jogadas;
    private Jogador vencedor;
    private boolean finalizada;
    private String resultado;
    
    public State() {
        this.jogadas = new ArrayList<>();
    }

    public boolean verificarSeJogadorJaJogou(Jogador jogador){
        return !jogadas.isEmpty() && jogadas.stream().anyMatch(j -> j.getJogador().equals(jogador));
    }

    public State jogarPapel(Jogador jogador) {   
        return new PartidaPapelState(this, jogador);
    }

    public State jogarTesoura(Jogador jogador) {
        return new PartidaTesouraState(this, jogador);
    }

    public State jogarPedra(Jogador jogador) {
        return new PartidaPedraState(this, jogador);
    }
    
    public State jogarSpock(Jogador jogador) {
        return new PartidaSpockState(this, jogador);
    }
    
    public State jogarLagarto(Jogador jogador) {
        return new PartidaLagartoState(this, jogador);
    }

    public void adicionarJogada(String tipo, Jogador jogador){
		this.getJogadas().add(new Jogada(tipo,jogador));
    }

    public State finalizar() {
        this.finalizada = true;
        return this;
    }

    public List<Jogada> getJogadas() {
        return jogadas;
    }

    public void setJogadas(List<Jogada> jogadas) {
        this.jogadas = jogadas;
    }

    public Jogador getVencedor() {
        return vencedor;
    }

    public void setVencedor(Jogador vencedor) {
        this.vencedor = vencedor;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

}