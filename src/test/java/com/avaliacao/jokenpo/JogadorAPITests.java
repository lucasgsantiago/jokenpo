package com.avaliacao.jokenpo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.avaliacao.jokenpo.application.commandService.JogadorCommandService;
import com.avaliacao.jokenpo.application.commandService.PartidaCommandService;
import com.avaliacao.jokenpo.application.queryService.JogadorQueryService;
import com.avaliacao.jokenpo.command.jogador.AdicinarJogadorCommand;
import com.avaliacao.jokenpo.helpers.BusinessException;
import com.avaliacao.jokenpo.queries.jogador.JogadorListResult;
import com.avaliacao.jokenpo.queries.jogador.JogadorResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JogadorAPITests {
    
    @Autowired
    private PartidaCommandService partidaCommandService;
    @Autowired
    private JogadorCommandService jogadorCommandService;
    @Autowired
    private JogadorQueryService jogadorQueryService;

    @LocalServerPort
	private int randomServerPort;
	private RestTemplate restTemplate;
	private String baseUrl;
	private HttpHeaders headers;
	private URI uri;
	private ObjectMapper mapper = new ObjectMapper();
    
    List<String> jogadores = new ArrayList<>();

    @BeforeEach
    public void setUp() throws BusinessException, URISyntaxException {
        restTemplate = new RestTemplate();
		baseUrl = "http://localhost:" + randomServerPort + "/api/v1/jogadores";
		headers = new HttpHeaders();
		uri = new URI(baseUrl);
        jogadores = new ArrayList<>();
        jogadores.add(jogadorCommandService.adicionarJogador(new AdicinarJogadorCommand("Jogador 1")));
        jogadores.add(jogadorCommandService.adicionarJogador(new AdicinarJogadorCommand("Jogador 2")));
        jogadores.add(jogadorCommandService.adicionarJogador(new AdicinarJogadorCommand("Jogador 3")));
    }

    @AfterEach
    public void tearDown(){
        partidaCommandService.resetarJogo();
    }

    @Test
    @DisplayName("Listar Jogadores")
	public void listarJogadoresTest() throws IOException {
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		JogadorListResult lista = mapper.readValue(result.getBody(), JogadorListResult.class);
		assertEquals(200, result.getStatusCodeValue());
		assertFalse(lista.getJogadores().isEmpty());
		assertEquals(lista.getQtdTotal(), 3);
    }

    
    @Test
    @DisplayName("Obter Jogador")
	public void obterJogadorTest() throws IOException, URISyntaxException {
        uri = new URI(baseUrl.concat("/").concat(jogadores.get(0)));
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		JogadorResult jogador = mapper.readValue(result.getBody(), JogadorResult.class);
        assertEquals(200, result.getStatusCodeValue());
		assertEquals(jogador.getId(), jogadores.get(0));
    }
    
    @Test
    @DisplayName("Adicionar Jogador")
    void adicionarJogadorSucessTest() throws BusinessException {
        AdicinarJogadorCommand command = new AdicinarJogadorCommand("Jogador Teste");
        HttpEntity<AdicinarJogadorCommand> request = new HttpEntity<>(command, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
        assertEquals(200, result.getStatusCodeValue());
    }
    
    @Test
    @DisplayName("Remover Jogador")
    void removerJogadorSuccessTest() throws Exception {
        restTemplate.delete(uri + "/{id}", jogadores.get(0));
        assertEquals(jogadorQueryService.listarJogadores().getQtdTotal(), 2);
    }

}
