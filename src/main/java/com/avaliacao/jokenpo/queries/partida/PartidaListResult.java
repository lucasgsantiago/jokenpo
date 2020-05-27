package com.avaliacao.jokenpo.queries.partida;

import java.util.ArrayList;
import java.util.List;

public class PartidaListResult {
    private int qtdTotal;
    private List<PartidaResult> partidas;
    
    public PartidaListResult(List<PartidaResult> partidas) {
        this.partidas = partidas;
        this.qtdTotal = partidas.size();
    }
    public PartidaListResult() {
        this.partidas = new ArrayList<>();
        this.qtdTotal = 0;
    }

    public int getQtdTotal() {
        return qtdTotal;
    }

    public void setQtdTotal(int qtdTotal) {
        this.qtdTotal = qtdTotal;
    }

    public List<PartidaResult> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<PartidaResult> partidas) {
        this.partidas = partidas;
    }
    
}