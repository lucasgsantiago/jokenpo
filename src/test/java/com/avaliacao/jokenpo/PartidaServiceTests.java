package com.avaliacao.jokenpo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import com.avaliacao.jokenpo.application.commandService.JogadorCommandService;
import com.avaliacao.jokenpo.application.commandService.PartidaCommandService;
import com.avaliacao.jokenpo.application.queryService.PartidaQueryService;
import com.avaliacao.jokenpo.command.jogador.AdicinarJogadorCommand;
import com.avaliacao.jokenpo.domain.partida.State;
import com.avaliacao.jokenpo.helpers.BusinessException;
import com.avaliacao.jokenpo.helpers.ResourceNotFoundException;
import com.avaliacao.jokenpo.queries.partida.JogadaResult;
import com.avaliacao.jokenpo.queries.partida.PartidaResult;

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
class PartidaServiceTests {

    @Autowired
    JogadorCommandService jogadorCommandService;

    @Autowired
    PartidaCommandService commandService;
    
    @Autowired
    PartidaQueryService queryService;

    List<String> jogadores = new ArrayList<>();

    @BeforeEach
    public void setUp() throws BusinessException {
        jogadores = new ArrayList<>();
        jogadores.add(jogadorCommandService.adicionarJogador(new AdicinarJogadorCommand("Jogador 1")));
        jogadores.add(jogadorCommandService.adicionarJogador(new AdicinarJogadorCommand("Jogador 2")));
        jogadores.add(jogadorCommandService.adicionarJogador(new AdicinarJogadorCommand("Jogador 3")));
        jogadores.add(jogadorCommandService.adicionarJogador(new AdicinarJogadorCommand("Jogador 4")));
        jogadores.add(jogadorCommandService.adicionarJogador(new AdicinarJogadorCommand("Jogador 5")));
    }

    @AfterEach
    public void tearDown(){
        commandService.resetarJogo();
    }

    @Test
    @DisplayName("Papel e Pedra -> Papel")
    void jogarPapelPedraTest()  throws BusinessException, ResourceNotFoundException {
        commandService.lancarJogadaPapel(jogadores.get(0));
        commandService.lancarJogadaPedra(jogadores.get(1));

        PartidaResult result = commandService.jogar();

        assertTrue(result.getResultado().equals(State.RESULTADO_VITORIA));
        assertTrue(result.getVencedor().getId().equals(jogadores.get(0)));
    }

    @Test
    @DisplayName("Papel e Tesoura -> Tesoura")
    void jogarPapelTesouraTest() throws BusinessException, ResourceNotFoundException {

        commandService.lancarJogadaPapel(jogadores.get(0));
        commandService.lancarJogadaTesoura(jogadores.get(1));

        PartidaResult result = commandService.jogar();

        assertTrue(result.getResultado().equals(State.RESULTADO_VITORIA));
        assertTrue(result.getVencedor().getId().equals(jogadores.get(1)));
       
    }
    
    @Test
    @DisplayName("Papel e Lagarto -> Lagarto")
    void jogarPapelLagartoTest() throws BusinessException, ResourceNotFoundException {

        commandService.lancarJogadaPapel(jogadores.get(0));
        commandService.lancarJogadaLagarto(jogadores.get(1));

        PartidaResult result = commandService.jogar();

        assertTrue(result.getResultado().equals(State.RESULTADO_VITORIA));
        assertTrue(result.getVencedor().getId().equals(jogadores.get(1)));
       
    }
    
    @Test
    @DisplayName("Papel e Spock -> Papel")
    void jogarPapelSpockTest() throws BusinessException, ResourceNotFoundException {

        commandService.lancarJogadaPapel(jogadores.get(0));
        commandService.lancarJogadaSpock(jogadores.get(1));

        PartidaResult result = commandService.jogar();

        assertTrue(result.getResultado().equals(State.RESULTADO_VITORIA));
        assertTrue(result.getVencedor().getId().equals(jogadores.get(0)));
        
    }

    @Test
    @DisplayName("Papel e Papel -> EMPATE")
    void jogarPapelPapelTest() throws BusinessException, ResourceNotFoundException {

        commandService.lancarJogadaPapel(jogadores.get(0));
        commandService.lancarJogadaPapel(jogadores.get(1));

        PartidaResult result = commandService.jogar();

        assertTrue(result.getResultado().equals(State.RESULTADO_EMPATE));
        assertNull(result.getVencedor());
      
    }

    @Test
    @DisplayName("Pedra e Tesoura -> Pedra")
    void jogarPedraTesouraTest() throws BusinessException, ResourceNotFoundException {

        commandService.lancarJogadaPedra(jogadores.get(0));
        commandService.lancarJogadaTesoura(jogadores.get(1));

        PartidaResult result = commandService.jogar();

        assertTrue(result.getResultado().equals(State.RESULTADO_VITORIA));
        assertTrue(result.getVencedor().getId().equals(jogadores.get(0)));
       
    }

    
    @Test
    @DisplayName("Pedra e Lagarto -> Pedra")
    void jogarPedraLagartoTest()  throws BusinessException, ResourceNotFoundException {

        commandService.lancarJogadaPedra(jogadores.get(0));
        commandService.lancarJogadaLagarto(jogadores.get(1));

        PartidaResult result = commandService.jogar();

        assertTrue(result.getResultado().equals(State.RESULTADO_VITORIA));
        assertTrue(result.getVencedor().getId().equals(jogadores.get(0)));
       
    }
    
    @Test
    @DisplayName("Pedra e Spock -> Spock")
    void jogarPedraSpockTest()  throws BusinessException, ResourceNotFoundException {

        commandService.lancarJogadaPedra(jogadores.get(0));
        commandService.lancarJogadaSpock(jogadores.get(1));

        PartidaResult result = commandService.jogar();

        assertTrue(result.getResultado().equals(State.RESULTADO_VITORIA));
        assertTrue(result.getVencedor().getId().equals(jogadores.get(1)));
    }

    @Test
    @DisplayName("Pedra e Pedra -> EMPATE")
    void jogarPedraPedraTest()  throws BusinessException, ResourceNotFoundException {

        commandService.lancarJogadaPedra(jogadores.get(0));
        commandService.lancarJogadaPedra(jogadores.get(1));

        PartidaResult result = commandService.jogar();

        assertTrue(result.getResultado().equals(State.RESULTADO_EMPATE));
        assertNull(result.getVencedor());
    }

    @Test
    @DisplayName("Tesoura e Tesoura -> EMPATE")
    void jogarTesouraTesouraTest()  throws BusinessException, ResourceNotFoundException {

        commandService.lancarJogadaTesoura(jogadores.get(0));
        commandService.lancarJogadaTesoura(jogadores.get(1));

        PartidaResult result = commandService.jogar();

        assertTrue(result.getResultado().equals(State.RESULTADO_EMPATE));
        assertNull(result.getVencedor());
    }

    @Test
    @DisplayName("Pedra, Tesoura e Tesoura -> Pedra")
    void jogarPedraTesouraTesouraTest() throws BusinessException, ResourceNotFoundException {

        commandService.lancarJogadaPedra(jogadores.get(0));
        commandService.lancarJogadaTesoura(jogadores.get(1));
        commandService.lancarJogadaTesoura(jogadores.get(2));

        PartidaResult result = commandService.jogar();

        assertTrue(result.getResultado().equals(State.RESULTADO_VITORIA));
        assertTrue(result.getVencedor().getId().equals(jogadores.get(0)));

    }

    
    @Test
    @DisplayName("Pedra, Tesoura e Papel -> Pedra")
    void jogarPedraTesouraPapelTest() throws BusinessException, ResourceNotFoundException {

        commandService.lancarJogadaPedra(jogadores.get(0));
        commandService.lancarJogadaTesoura(jogadores.get(1));
        commandService.lancarJogadaPapel(jogadores.get(2));

        PartidaResult result = commandService.jogar();

        assertTrue(result.getResultado().equals(State.RESULTADO_EMPATE));
        assertNull(result.getVencedor());

    }

    
    @Test
    @DisplayName("Spock e Lagarto -> Lagarto")
    void jogarSpockLagartoTest()  throws BusinessException, ResourceNotFoundException {

        commandService.lancarJogadaSpock(jogadores.get(0));
        commandService.lancarJogadaLagarto(jogadores.get(1));

        PartidaResult result = commandService.jogar();

        assertTrue(result.getResultado().equals(State.RESULTADO_VITORIA));
        assertTrue(result.getVencedor().getId().equals(jogadores.get(1)));
    }
   
    @Test
    @DisplayName("Spock e Tesoura -> Spock")
    void jogarSpockTesouraTest()  throws BusinessException, ResourceNotFoundException {

        commandService.lancarJogadaSpock(jogadores.get(0));
        commandService.lancarJogadaTesoura(jogadores.get(1));

        PartidaResult result = commandService.jogar();

        assertTrue(result.getResultado().equals(State.RESULTADO_VITORIA));
        assertTrue(result.getVencedor().getId().equals(jogadores.get(0)));
    }
   
    @Test
    @DisplayName("Spock e Spock -> EMPATE")
    void jogarSpockSpockTest()  throws BusinessException, ResourceNotFoundException {

        commandService.lancarJogadaSpock(jogadores.get(0));
        commandService.lancarJogadaSpock(jogadores.get(1));

        PartidaResult result = commandService.jogar();

        assertTrue(result.getResultado().equals(State.RESULTADO_EMPATE));
        assertNull(result.getVencedor());
    }

    @Test
    @DisplayName("Lagarto e Lagarto -> EMPATE")
    void jogarLagartoLagartoTest()  throws BusinessException, ResourceNotFoundException {

        commandService.lancarJogadaLagarto(jogadores.get(0));
        commandService.lancarJogadaLagarto(jogadores.get(1));

        PartidaResult result = commandService.jogar();

        assertTrue(result.getResultado().equals(State.RESULTADO_EMPATE));
        assertNull(result.getVencedor());
    }

    @Test
    @DisplayName("Todos diferentes -> EMPATE")
    void jogarPedraPapelTesouraLagartoSpockTest() throws BusinessException, ResourceNotFoundException {

        commandService.lancarJogadaPedra(jogadores.get(0));
        commandService.lancarJogadaPapel(jogadores.get(1));
        commandService.lancarJogadaTesoura(jogadores.get(2));
        commandService.lancarJogadaLagarto(jogadores.get(3));
        commandService.lancarJogadaSpock(jogadores.get(4));

        PartidaResult result = commandService.jogar();

        assertTrue(result.getResultado().equals(State.RESULTADO_EMPATE));
        assertNull(result.getVencedor());
    }

    @Test
    @DisplayName("Todos iguais (Pedra)-> EMPATE")
    void jogarTodosIguaisTest() throws BusinessException, ResourceNotFoundException {

        commandService.lancarJogadaPedra(jogadores.get(0));
        commandService.lancarJogadaPedra(jogadores.get(1));
        commandService.lancarJogadaPedra(jogadores.get(2));
        commandService.lancarJogadaPedra(jogadores.get(3));
        commandService.lancarJogadaPedra(jogadores.get(4));

        PartidaResult result = commandService.jogar();

        assertTrue(result.getResultado().equals(State.RESULTADO_EMPATE));
        assertNull(result.getVencedor());
    }

    
    @Test
    @DisplayName("Listar jogadas")
    void listarJogadasTest() throws BusinessException, ResourceNotFoundException {

        commandService.lancarJogadaPedra(jogadores.get(0));
        commandService.lancarJogadaPapel(jogadores.get(1));

        List<JogadaResult> lista = queryService.listarJogadasDaPartidaAtual();

        assertNotNull(lista);
        assertTrue(lista.size() == 2);
    }
    
    @Test
    @DisplayName("Remover jogada")
    void removerJogadaTest() throws BusinessException, ResourceNotFoundException {

        commandService.lancarJogadaPedra(jogadores.get(0));
        commandService.lancarJogadaPapel(jogadores.get(1));
        List<JogadaResult> listaAntes = queryService.listarJogadasDaPartidaAtual();

        commandService.removerJogada(jogadores.get(0));
        List<JogadaResult> listaDepois = queryService.listarJogadasDaPartidaAtual();

        assertNotNull(listaAntes);
        assertNotNull(listaDepois);
        assertTrue(listaAntes.size() == 2);
        assertTrue(listaDepois.size() == 1);
        assertTrue(listaDepois.get(0).getJogador().getId().equals(jogadores.get(1)));
    }
}
