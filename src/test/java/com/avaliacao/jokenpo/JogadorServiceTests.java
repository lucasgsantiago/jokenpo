package com.avaliacao.jokenpo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import com.avaliacao.jokenpo.application.commandService.JogadorCommandService;
import com.avaliacao.jokenpo.application.commandService.PartidaCommandService;
import com.avaliacao.jokenpo.application.queryService.JogadorQueryService;
import com.avaliacao.jokenpo.command.jogador.AdicinarJogadorCommand;
import com.avaliacao.jokenpo.helpers.BusinessException;
import com.avaliacao.jokenpo.helpers.ResourceNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JogadorServiceTests {

    @Autowired
    JogadorCommandService commandService;

    @Autowired
    JogadorQueryService queryService;

    @Autowired
    PartidaCommandService partidaCommandService;
    
    List<String> jogadores = new ArrayList<>();

    @BeforeEach
    public void setUp() throws BusinessException {
        jogadores = new ArrayList<>();
        jogadores.add(commandService.adicionarJogador(new AdicinarJogadorCommand("Jogador 1")));
        jogadores.add(commandService.adicionarJogador(new AdicinarJogadorCommand("Jogador 2")));
        jogadores.add(commandService.adicionarJogador(new AdicinarJogadorCommand("Jogador 3")));
    }

    @AfterEach
    public void tearDown(){
        partidaCommandService.resetarJogo();
    }

    @Test
    @DisplayName("Adicionar Jogador")
    void adicionarJogadorSucessTest() throws BusinessException {
        AdicinarJogadorCommand command = new AdicinarJogadorCommand("Jogador Teste");
        assertNotNull(commandService.adicionarJogador(command));
        assertTrue(queryService.listarJogadores().getJogadores().size() == 4);
    }

    @Test
    @DisplayName("Adicionar Jogador com nome vazio")
    void adicionarJogadorErrorNomeVazioTest() {
        AdicinarJogadorCommand command = new AdicinarJogadorCommand("");
        try {
            commandService.adicionarJogador(command);
            fail();
        } catch (BusinessException ex) {
			assertTrue(ex.getMessage().contains("O nome não pode ser nulo ou vazio!"));
        }
    }

    @Test()
    @DisplayName("Adicionar Jogador com nome nulo")
    void adicionarJogadorErrorNomeNullTest() {
        AdicinarJogadorCommand command = new AdicinarJogadorCommand(null);
        try {
            commandService.adicionarJogador(command);
            fail();
        } catch (BusinessException ex) {
			assertTrue(ex.getMessage().contains("O nome não pode ser nulo ou vazio!"));
        }
    }

    @Test
    @DisplayName("Remover Jogador")
    void removerJogadorSuccessTest() throws ResourceNotFoundException {
        commandService.removerJogador(jogadores.get(0));
        assertTrue(queryService.listarJogadores().getJogadores().size() == 2);
    }

}
