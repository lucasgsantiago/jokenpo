package com.avaliacao.jokenpo.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.avaliacao.jokenpo.domain.partida.Partida;
import com.avaliacao.jokenpo.domain.partida.State;
import com.avaliacao.jokenpo.helpers.BusinessException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Jogo {

    private static final int QTD_MINIMA_JOGADAS = 2;
    private static final int QTD_MAXIMA_JOGADAS = 5;

    private List<State> partidas;
    private List<Jogador> jogadores;
    private State partidaAtual;

    public Jogo() {
        partidas = new ArrayList<>();
        jogadores = new ArrayList<>();
        partidaAtual = new Partida();
    }

    public String adicionarJogador(String nome) throws BusinessException {
        if(jogadores.stream().anyMatch(j -> j.getNome().equals(nome))){
            throw new BusinessException("Já existe um jogador com este nome!");
        }

        Jogador jogador = new Jogador(nome);
        jogadores.add(jogador);
        return jogador.getId();
    }

    public void lancarJogadaPapel(String jogadorId) throws BusinessException {
        Jogador jogador = verificarSeExisteJogador(jogadorId);
        verificarSeJogadorJaJogouNaPartida(jogador);
        this.partidaAtual = partidaAtual.jogarPapel(jogador);
    }

    public void lancarJogadaPedra(String jogadorId) throws BusinessException {
        Jogador jogador = verificarSeExisteJogador(jogadorId);
        verificarSeJogadorJaJogouNaPartida(jogador);
        this.partidaAtual = partidaAtual.jogarPedra(jogador);
    }

    public void lancarJogadaTesoura(String jogadorId) throws BusinessException {
        Jogador jogador = verificarSeExisteJogador(jogadorId);
        verificarSeJogadorJaJogouNaPartida(jogador);
        this.partidaAtual = partidaAtual.jogarTesoura(jogador);
    }

    public void lancarJogadaSpock(String jogadorId) throws BusinessException {
        Jogador jogador = verificarSeExisteJogador(jogadorId);
        verificarSeJogadorJaJogouNaPartida(jogador);
        this.partidaAtual = partidaAtual.jogarSpock(jogador);
    }

    public void lancarJogadaLagarto(String jogadorId) throws BusinessException {
        Jogador jogador = verificarSeExisteJogador(jogadorId);
        verificarSeJogadorJaJogouNaPartida(jogador);
        this.partidaAtual = partidaAtual.jogarLagarto(jogador);
    }

    private Jogador verificarSeExisteJogador(String jogadorId) throws BusinessException {
        Optional<Jogador> chkJogador = this.jogadores.stream().filter(j -> j.getId().equals(jogadorId)).findFirst();
        if(!chkJogador.isPresent()){
            throw new BusinessException("Não há nenhum jogador com o Id informado.");
        }

        Jogador jogador = chkJogador.get();
        return jogador;
    }

    private void verificarSeJogadorJaJogouNaPartida(Jogador jogador) throws BusinessException {
        if(this.partidaAtual.verificarSeJogadorJaJogou(jogador)){
            throw new BusinessException("Este jogador já fez sua jogada!");
        }
    }

    public State jogar() throws BusinessException {
        verificarQuantidadeMinimaDeJogadas();
        State partida = this.partidaAtual.finalizar();
        this.partidas.add(partida);
        reiniciarPartida();
       return partida;
    }


    private boolean verificarSeTodasAsJogadasSaoDiferentes(){
        long qtdJogadas = partidaAtual.getJogadas().size();
        long qtdJogadasDiferentes = partidaAtual.getJogadas().stream().map(j -> j.getTipo()).distinct().count();
        return qtdJogadas == qtdJogadasDiferentes && qtdJogadasDiferentes == QTD_MAXIMA_JOGADAS;
    }

    private void verificarQuantidadeMinimaDeJogadas() throws BusinessException {
        if(partidaAtual.getJogadas().size() < QTD_MINIMA_JOGADAS){
            throw new BusinessException("Deve haver no mínimo "+ QTD_MINIMA_JOGADAS+ "jogadas!");
        }
    }
    
	public void removerJogada(String jogadorId) throws BusinessException {
        Jogador jogador = verificarSeExisteJogador(jogadorId);
        this.partidaAtual.getJogadas().removeIf(j -> j.getJogador().equals(jogador));
	}

	public void removerJogador(String jogadorId) throws BusinessException {
        Jogador jogador = verificarSeExisteJogador(jogadorId);
        this.partidaAtual.getJogadas().removeIf(j -> j.getJogador().equals(jogador));
        this.jogadores.remove(jogador);
	}

	public Jogador obterJogador(String jogadorId) throws BusinessException {
		return verificarSeExisteJogador(jogadorId);
	}

    public void reiniciarPartida() {
        this.partidaAtual = new Partida();
    }

    public State getPartidaAtual() {
        return partidaAtual;
    }

    public void setPartidaAtual(State partidaAtual) {
        this.partidaAtual = partidaAtual;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public List<State> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<State> partidas) {
        this.partidas = partidas;
    }



}