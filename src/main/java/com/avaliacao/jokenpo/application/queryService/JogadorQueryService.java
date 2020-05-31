package com.avaliacao.jokenpo.application.queryService;

import java.util.ArrayList;
import java.util.List;

import com.avaliacao.jokenpo.command.jogador.AdicinarJogadorCommand;
import com.avaliacao.jokenpo.converter.JogadorConverter;
import com.avaliacao.jokenpo.domain.Jogador;
import com.avaliacao.jokenpo.domain.Jogo;
import com.avaliacao.jokenpo.helpers.BusinessException;
import com.avaliacao.jokenpo.helpers.ResourceNotFoundException;
import com.avaliacao.jokenpo.queries.jogador.JogadorListResult;
import com.avaliacao.jokenpo.queries.jogador.JogadorResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JogadorQueryService {

    @Autowired
    private Jogo jogo;
 
    public JogadorListResult listarJogadores() {

        List<Jogador> jogadores = jogo.getJogadores();

        if (jogadores.isEmpty()) {
            return new JogadorListResult();
        }

        List<JogadorResult> jogadoresResult = new ArrayList<>();

        jogadores.forEach(j -> jogadoresResult.add(new JogadorResult(j.getId(), j.getNome())));

        return new JogadorListResult(jogadoresResult);

    }

	public JogadorResult obterJogador(String jogadorId) throws ResourceNotFoundException {
        return JogadorConverter.converter(jogo.obterJogador(jogadorId));
	}
}