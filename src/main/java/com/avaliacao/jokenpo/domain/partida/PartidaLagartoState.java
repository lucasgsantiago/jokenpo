package com.avaliacao.jokenpo.domain.partida;

import com.avaliacao.jokenpo.domain.Jogador;

public class PartidaLagartoState extends State {
	public final static String TIPO_JOGADA = "LAGARTO";
	
    public PartidaLagartoState() { 
        super();
    }

    public PartidaLagartoState(State estadoAnterior, Jogador jogador) {
        this.setJogadas(estadoAnterior.getJogadas());
		this.adicionarJogada(TIPO_JOGADA,jogador);
        this.setFinalizada(estadoAnterior.isFinalizada());
		this.setVencedor(jogador);		
		this.setResultado(RESULTADO_VITORIA);
    }

	@Override
	public State jogarPapel(Jogador jogador) {
        this.adicionarJogada(PartidaPapelState.TIPO_JOGADA,jogador);
		return this;
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
        this.adicionarJogada(PartidaSpockState.TIPO_JOGADA,jogador);
		return this;
	}

	@Override
	public State jogarLagarto(Jogador jogador) {
		this.adicionarJogada(PartidaLagartoState.TIPO_JOGADA,jogador);
		this.setResultado(RESULTADO_EMPATE);
		this.setVencedor(null);
		return this;
	}
	
	
}