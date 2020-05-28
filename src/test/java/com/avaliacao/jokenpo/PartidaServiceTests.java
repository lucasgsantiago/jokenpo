package com.avaliacao.jokenpo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import com.avaliacao.jokenpo.command.jogador.AdicinarJogadorCommand;
import com.avaliacao.jokenpo.domain.partida.State;
import com.avaliacao.jokenpo.helpers.BusinessException;
import com.avaliacao.jokenpo.helpers.ResourceNotFoundException;
import com.avaliacao.jokenpo.queries.partida.PartidaResult;
import com.avaliacao.jokenpo.service.JogadorService;
import com.avaliacao.jokenpo.service.PartidaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PartidaServiceTests {

    @Autowired
    JogadorService jogadorService;

    @Autowired
    PartidaService partidaService;

    List<String> jogadores = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        jogadores = new ArrayList<>();
        try {
            jogadores.add(jogadorService.adicionarJogador(new AdicinarJogadorCommand("Jogador 1")));
            jogadores.add(jogadorService.adicionarJogador(new AdicinarJogadorCommand("Jogador 2")));
            jogadores.add(jogadorService.adicionarJogador(new AdicinarJogadorCommand("Jogador 3")));
        } catch (BusinessException e) {
            fail();
        }

    }

    @Test
    void adicionarJogadaSucessTest() {

        try {
            partidaService.lancarJogadaPedra(jogadores.get(0));
            partidaService.lancarJogadaTesoura(jogadores.get(1));
            partidaService.lancarJogadaTesoura(jogadores.get(2));

            PartidaResult result = partidaService.jogar();

            assertTrue(result.getResultado().equals(State.RESULTADO_VITORIA));
            assertTrue(result.getVencedor().getId().equals(jogadores.get(0)));
        } catch (BusinessException | ResourceNotFoundException e) {
            fail();
        }

    }


   
}
