package com.avaliacao.jokenpo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.avaliacao.jokenpo.application.commandService.JogadorCommandService;
import com.avaliacao.jokenpo.application.commandService.PartidaCommandService;
import com.avaliacao.jokenpo.application.queryService.PartidaQueryService;
import com.avaliacao.jokenpo.command.jogador.AdicinarJogadorCommand;
import com.avaliacao.jokenpo.domain.partida.PartidaPapelState;
import com.avaliacao.jokenpo.domain.partida.PartidaPedraState;
import com.avaliacao.jokenpo.helpers.BusinessException;
import com.avaliacao.jokenpo.helpers.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.Matchers.containsString;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class JogadaAPITests {
    
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PartidaCommandService partidaCommandService;
    @Autowired
    private PartidaQueryService partidaQueryService;
    @Autowired
    private JogadorCommandService jogadorCommandService;

    @LocalServerPort
	private int randomServerPort;
	private String baseUrl;
    
    List<String> jogadores = new ArrayList<>();

    @BeforeEach
    public void setUp() throws BusinessException, URISyntaxException, ResourceNotFoundException {
        baseUrl = "http://localhost:" + randomServerPort + "/api/v1/jogadas";
        
        jogadores = new ArrayList<>();

        jogadores.add(jogadorCommandService.adicionarJogador(new AdicinarJogadorCommand("Jogador 1")));
        jogadores.add(jogadorCommandService.adicionarJogador(new AdicinarJogadorCommand("Jogador 2")));
        jogadores.add(jogadorCommandService.adicionarJogador(new AdicinarJogadorCommand("Jogador 3")));

        partidaCommandService.lancarJogadaPedra(jogadores.get(0));
    }

    @AfterEach
    public void tearDown(){
        partidaCommandService.resetarJogo();
    }

    @Test
    @DisplayName("Listar Jogadas")
	public void listarJogadasTest() throws Exception {
        
        ResultActions response = mockMvc.perform(
            get(baseUrl)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(PartidaPedraState.TIPO_JOGADA)));
    }
    
    @Test
    @DisplayName("Jogada Papel")
    void lancarJogadaPapelTest() throws Exception {
        mockMvc.perform(post(
            baseUrl.concat("/papel/jogador/{jogadorId}"), jogadores.get(1))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        assertEquals(partidaQueryService.listarJogadasDaPartidaAtual().size(),2);
        assertTrue(partidaQueryService.listarJogadasDaPartidaAtual().stream()
            .anyMatch(j -> j.getTipo().equals(PartidaPapelState.TIPO_JOGADA)));
    }
    
    @Test
    @DisplayName("Remover Jogada")
    void removerJogadaSuccessTest() throws Exception {
        assertEquals(partidaQueryService.listarJogadasDaPartidaAtual().size(),1);

        mockMvc.perform(
            delete(baseUrl.concat("/jogador/{jogadorId}"), jogadores.get(0))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk());

        assertEquals(partidaQueryService.listarJogadasDaPartidaAtual().size(),0);
    }

}
