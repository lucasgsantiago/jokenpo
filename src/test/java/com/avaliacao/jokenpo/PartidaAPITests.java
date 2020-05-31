package com.avaliacao.jokenpo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.avaliacao.jokenpo.application.commandService.JogadorCommandService;
import com.avaliacao.jokenpo.application.commandService.PartidaCommandService;
import com.avaliacao.jokenpo.application.queryService.JogadorQueryService;
import com.avaliacao.jokenpo.application.queryService.PartidaQueryService;
import com.avaliacao.jokenpo.command.jogador.AdicinarJogadorCommand;
import com.avaliacao.jokenpo.domain.partida.State;
import com.avaliacao.jokenpo.helpers.BusinessException;
import com.avaliacao.jokenpo.helpers.ResourceNotFoundException;
import com.avaliacao.jokenpo.queries.jogador.JogadorListResult;
import com.avaliacao.jokenpo.queries.jogador.JogadorResult;
import com.avaliacao.jokenpo.queries.partida.PartidaResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PartidaAPITests {

    @Autowired
    private PartidaCommandService partidaCommandService;
    @Autowired
    private JogadorCommandService jogadorCommandService;
    @Autowired
    private PartidaQueryService partidaQueryService;

    @LocalServerPort
    private int randomServerPort;
    private RestTemplate restTemplate;
    private String baseUrl;
    private HttpHeaders headers;
    private URI uri;
    private ObjectMapper mapper = new ObjectMapper();

    List<String> jogadores = new ArrayList<>();

    @BeforeEach
    public void setUp() throws BusinessException, URISyntaxException, ResourceNotFoundException {
        restTemplate = new RestTemplate();
        baseUrl = "http://localhost:" + randomServerPort + "/api/v1/partidas";
        headers = new HttpHeaders();
        uri = new URI(baseUrl);
        jogadores.add(jogadorCommandService.adicionarJogador(new AdicinarJogadorCommand("Jogador 1")));
        jogadores.add(jogadorCommandService.adicionarJogador(new AdicinarJogadorCommand("Jogador 2")));
        jogadores.add(jogadorCommandService.adicionarJogador(new AdicinarJogadorCommand("Jogador 3")));

        partidaCommandService.lancarJogadaPapel(jogadores.get(0));
        partidaCommandService.lancarJogadaPedra(jogadores.get(1));
    }

    @AfterEach
    public void tearDown() {
        partidaCommandService.resetarJogo();
        jogadores = new ArrayList<>();
    }

    @Test
    @DisplayName("Obter Partida Atual")
    public void obterJogadorTest() throws IOException, URISyntaxException {
        uri = new URI(baseUrl.concat("/atual"));
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        PartidaResult partida = mapper.readValue(result.getBody(), PartidaResult.class);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(partida.getJogadas().size(), 2);
    }

    @Test
    @DisplayName("Jogar")
    void jogarTest() throws BusinessException, URISyntaxException, JsonMappingException, JsonProcessingException {
        uri = new URI(baseUrl.concat("/jogar"));
        ResponseEntity<String> result = restTemplate.postForEntity(uri, null, String.class);
        PartidaResult partida = mapper.readValue(result.getBody(), PartidaResult.class);
        assertEquals(200, result.getStatusCodeValue());        
        assertEquals(partida.getVencedor().getId(), jogadores.get(0));        
        assertEquals(partida.getResultado(), State.RESULTADO_VITORIA);
    }
    
    @Test
    @DisplayName("Resetar")
    void resetarTest() throws Exception {
        uri = new URI(baseUrl.concat("/resetar"));
		ResponseEntity<String> result = restTemplate.postForEntity(uri, null, String.class);
        assertTrue(partidaQueryService.obterPartidaAtual().getJogadas().isEmpty());
        assertEquals(200, result.getStatusCodeValue());
    }

}
