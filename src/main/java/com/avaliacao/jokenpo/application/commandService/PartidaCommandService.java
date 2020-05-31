package com.avaliacao.jokenpo.application.commandService;

import java.util.ArrayList;
import java.util.List;

import com.avaliacao.jokenpo.converter.PartidaConverter;
import com.avaliacao.jokenpo.domain.Jogo;
import com.avaliacao.jokenpo.domain.partida.State;
import com.avaliacao.jokenpo.helpers.BusinessException;
import com.avaliacao.jokenpo.helpers.ResourceNotFoundException;
import com.avaliacao.jokenpo.queries.jogador.JogadorResult;
import com.avaliacao.jokenpo.queries.partida.JogadaResult;
import com.avaliacao.jokenpo.queries.partida.PartidaResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidaCommandService {

    @Autowired
    private Jogo jogo;

    public void lancarJogadaPapel(String jogadorId) throws BusinessException, ResourceNotFoundException {
        jogo.lancarJogadaPapel(jogadorId);
    }
    
    public void lancarJogadaPedra(String jogadorId) throws BusinessException, ResourceNotFoundException {
        jogo.lancarJogadaPedra(jogadorId);
    }
    
    public void lancarJogadaTesoura(String jogadorId) throws BusinessException, ResourceNotFoundException {
        jogo.lancarJogadaTesoura(jogadorId);
    }
    
    public void lancarJogadaSpock(String jogadorId) throws BusinessException, ResourceNotFoundException {
        jogo.lancarJogadaSpock(jogadorId);
    }
    
    public void lancarJogadaLagarto(String jogadorId) throws BusinessException, ResourceNotFoundException {
        jogo.lancarJogadaLagarto(jogadorId);
    }

    public PartidaResult jogar() throws BusinessException {
        State partidaAtual = jogo.jogar();
        PartidaResult partidaResult = PartidaConverter.converter(partidaAtual);
        return partidaResult;
    }

	public void removerJogada(String jogadorId) throws ResourceNotFoundException {
        jogo.removerJogada(jogadorId);
	}

    public void resetarJogo(){
        jogo.resetarJogo();
    }
}