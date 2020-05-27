package com.avaliacao.jokenpo.converter;

import com.avaliacao.jokenpo.command.jogador.AdicinarJogadorCommand;
import com.avaliacao.jokenpo.domain.Jogador;
import com.avaliacao.jokenpo.queries.jogador.JogadorResult;

public class JogadorConverter {
    public static JogadorResult converter(Jogador domain){

        JogadorResult jogadorRestul = new JogadorResult(domain.getId(),domain.getNome());

        return jogadorRestul;        
    }

    public static Jogador converter(AdicinarJogadorCommand command){

        Jogador domain = new Jogador(command.getNome());

        return domain;        
    }
}