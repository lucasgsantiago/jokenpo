package com.avaliacao.jokenpo.converter;

import com.avaliacao.jokenpo.domain.partida.State;
import com.avaliacao.jokenpo.queries.jogador.JogadorResult;
import com.avaliacao.jokenpo.queries.partida.JogadaResult;
import com.avaliacao.jokenpo.queries.partida.PartidaResult;

public class PartidaConverter {
    public static PartidaResult converter(State domain){

        PartidaResult partidaRestul = new PartidaResult();
        
        domain.getJogadas().forEach(j ->{
            JogadorResult jogador = new JogadorResult(j.getJogador().getId(), j.getJogador().getNome());
            partidaRestul.getJogadas().add(new JogadaResult(j.getTipo(),jogador));
        });

        if(domain.getVencedor() != null){
            partidaRestul.setVencedor(new JogadorResult(domain.getVencedor().getId(), domain.getVencedor().getNome()));
        }

        partidaRestul.setResultado(domain.getResultado());
        partidaRestul.setFinalizada(domain.isFinalizada());

        return partidaRestul;        
    }
}