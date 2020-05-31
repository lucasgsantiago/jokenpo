package com.avaliacao.jokenpo.application.queryService;

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
public class PartidaQueryService {

    @Autowired
    private Jogo jogo;

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

}