package com.avaliacao.jokenpo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.avaliacao.jokenpo.command.jogador.AdicinarJogadorCommand;
import com.avaliacao.jokenpo.helpers.BusinessException;
import com.avaliacao.jokenpo.service.JogadorService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JogadorServiceTests {

    @Autowired
    JogadorService jogadorService;

    @Test
    void contextLoads() {
    }

    @Test
    void adicionarJogadorSucessTest() {
        AdicinarJogadorCommand command = new AdicinarJogadorCommand("Jogador Teste");

        try {
            assertNotNull(jogadorService.adicionarJogador(command));
        } catch (BusinessException e) {
            fail();
        }

    }

    @Test
    void adicionarJogadorErrorVazioTest() {
        AdicinarJogadorCommand command = new AdicinarJogadorCommand("");

        try {
            jogadorService.adicionarJogador(command);
            fail();
        } catch (BusinessException ex) {
			assertTrue(ex.getMessage().contains("O nome não pode ser nulo ou vazio!"));
        }

    }

    @Test
    void adicionarJogadorErrorNullTest() {
        AdicinarJogadorCommand command = new AdicinarJogadorCommand(null);

        try {
            jogadorService.adicionarJogador(command);
            fail();
        } catch (BusinessException ex) {
			assertTrue(ex.getMessage().contains("O nome não pode ser nulo ou vazio!"));
        }

    }

    @Test
    void removerJogadorSuccessTest() {
        AdicinarJogadorCommand command = new AdicinarJogadorCommand(null);

        try {
            jogadorService.adicionarJogador(command);
            fail();
        } catch (BusinessException ex) {
			assertTrue(ex.getMessage().contains("O nome não pode ser nulo ou vazio!"));
        }

    }

}
