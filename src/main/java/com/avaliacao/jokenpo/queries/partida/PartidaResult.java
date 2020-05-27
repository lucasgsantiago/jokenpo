package com.avaliacao.jokenpo.queries.partida;

import java.util.ArrayList;
import java.util.List;

import com.avaliacao.jokenpo.queries.jogador.JogadorResult;

public class PartidaResult {
    
    private List<JogadaResult> jogadas;
    private JogadorResult vencedor;
    private boolean finalizada;
    private String resultado;

    public PartidaResult() {
        jogadas = new ArrayList<>();
    }

    public PartidaResult(List<JogadaResult> jogadas, JogadorResult vencedor, boolean finalizada) {
        this.jogadas = jogadas;
        this.vencedor = vencedor;
        this.finalizada = finalizada;
    }

    public List<JogadaResult> getJogadas() {
        return jogadas;
    }

    public void setJogadas(List<JogadaResult> jogadas) {
        this.jogadas = jogadas;
    }

    public JogadorResult getVencedor() {
        return vencedor;
    }

    public void setVencedor(JogadorResult vencedor) {
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