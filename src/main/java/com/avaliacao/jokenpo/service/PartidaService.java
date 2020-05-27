package com.avaliacao.jokenpo.service;

import java.util.ArrayList;
import java.util.List;

import com.avaliacao.jokenpo.converter.PartidaConverter;
import com.avaliacao.jokenpo.domain.Jogo;
import com.avaliacao.jokenpo.domain.partida.State;
import com.avaliacao.jokenpo.helpers.BusinessException;
import com.avaliacao.jokenpo.queries.jogador.JogadorResult;
import com.avaliacao.jokenpo.queries.partida.JogadaResult;
import com.avaliacao.jokenpo.queries.partida.PartidaResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {

    @Autowired
    private Jogo jogo;

    public void lancarJogadaPapel(String jogadorId) throws BusinessException {
        jogo.lancarJogadaPapel(jogadorId);
    }
    
    public void lancarJogadaPedra(String jogadorId) throws BusinessException {
        jogo.lancarJogadaPedra(jogadorId);
    }
    
    public void lancarJogadaTesoura(String jogadorId) throws BusinessException {    
        jogo.lancarJogadaTesoura(jogadorId);
    }
    
    public void lancarJogadaSpock(String jogadorId) throws BusinessException {    
        jogo.lancarJogadaSpock(jogadorId);
    }
    
    public void lancarJogadaLagarto(String jogadorId) throws BusinessException {   
        jogo.lancarJogadaLagarto(jogadorId);
    }

    public List<JogadaResult> listarJogadasDaPartidaAtual(){
        List<JogadaResult> jogadas = new ArrayList<>();

        jogo.getPartidaAtual().getJogadas().forEach(j ->{
            JogadorResult jogador = new JogadorResult(j.getJogador().getId(), j.getJogador().getNome());
            jogadas.add(new JogadaResult(j.getTipo(),jogador));
        });
       
        return jogadas;
    }

    public PartidaResult obterPartidaAtual(){
        PartidaResult partidaAtualRestul = new PartidaResult();

        State partidaAtual = jogo.getPartidaAtual();
        partidaAtual.getJogadas().forEach(j ->{
            JogadorResult jogador = new JogadorResult(j.getJogador().getId(), j.getJogador().getNome());
            partidaAtualRestul.getJogadas().add(new JogadaResult(j.getTipo(),jogador));
        });
        return partidaAtualRestul;
    }

    public PartidaResult jogar() throws BusinessException {
        State partidaAtual = jogo.jogar();
        PartidaResult partidaResult = PartidaConverter.converter(partidaAtual);
        return partidaResult;
    }

	public void removerJogada(String jogadorId) throws BusinessException {
        jogo.removerJogada(jogadorId);
	}

}