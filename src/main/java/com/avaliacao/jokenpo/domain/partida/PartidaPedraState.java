package com.avaliacao.jokenpo.domain.partida;

import com.avaliacao.jokenpo.domain.Jogador;

public class PartidaPedraState extends State {
	public final static String TIPO_JOGADA = "PEDRA";

    public PartidaPedraState() { 
        super();
    }

    public PartidaPedraState(State estadoAnterior, Jogador jogador) {
        this.setJogadas(estadoAnterior.getJogadas());
		this.adicionarJogada(TIPO_JOGADA,jogador);
        this.setFinalizada(estadoAnterior.isFinalizada());
		this.setVencedor(jogador);		
		this.setResultado(RESULTADO_VITORIA);
    }
	
	@Override
	public State jogarPapel(Jogador jogador) {
		return new PartidaPapelState(this, jogador);
	}

	@Override
	public State jogarTesoura(Jogador jogador) {
		this.adicionarJogada(PartidaTesouraState.TIPO_JOGADA,jogador);
		return this;
	}

	@Override
	public State jogarPedra(Jogador jogador) {
		this.adicionarJogada(PartidaPedraState.TIPO_JOGADA,jogador);
		this.setResultado(RESULTADO_EMPATE);
		this.setVencedor(null);
		return this;
	}

	@Override
	public State jogarSpock(Jogador jogador) {
		return new PartidaSpockState(this, jogador);
	}

	@Override
	public State jogarLagarto(Jogador jogador) {
		this.adicionarJogada(PartidaLagartoState.TIPO_JOGADA,jogador);
		return this;
	}

}