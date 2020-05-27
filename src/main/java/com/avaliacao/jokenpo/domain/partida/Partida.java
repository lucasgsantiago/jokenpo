package com.avaliacao.jokenpo.domain.partida;

import com.avaliacao.jokenpo.domain.Jogador;

public class Partida extends State {
    
	public final static String TIPO_JOGADA = "NENHUMA";
    public Partida() {
        super();
    }

    @Override
    public State jogarPapel(Jogador jogador) {   
        return new PartidaPapelState(this, jogador);
    }

    @Override
    public State jogarTesoura(Jogador jogador) {
        return new PartidaTesouraState(this, jogador);
    }

    @Override
    public State jogarPedra(Jogador jogador) {
        return new PartidaPedraState(this, jogador);
    }

    @Override
    public State jogarSpock(Jogador jogador) {
        return new PartidaSpockState(this, jogador);
    }
    @Override
    public State jogarLagarto(Jogador jogador) {
        return new PartidaLagartoState(this, jogador);
    }


}