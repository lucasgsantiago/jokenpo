package com.avaliacao.jokenpo.controller;

import java.util.List;

import com.avaliacao.jokenpo.application.commandService.PartidaCommandService;
import com.avaliacao.jokenpo.application.queryService.PartidaQueryService;
import com.avaliacao.jokenpo.helpers.BusinessException;
import com.avaliacao.jokenpo.helpers.ResourceNotFoundException;
import com.avaliacao.jokenpo.queries.partida.JogadaResult;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/jogadas")
public class JogadaController {
    
    private PartidaCommandService commandService;
    private PartidaQueryService queryService;
    
    public JogadaController(PartidaCommandService commandService, PartidaQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }
    
    @GetMapping
    public ResponseEntity<List<JogadaResult>> listarJogadas() {
        return ResponseEntity.ok(queryService.listarJogadasDaPartidaAtual());
    }

    @PostMapping("/papel/jogador/{jogadorId}")
    public ResponseEntity lancarJogadaPapel(@PathVariable("jogadorId") String jogadorId)
            throws BusinessException, ResourceNotFoundException {
        commandService.lancarJogadaPapel(jogadorId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/pedra/jogador/{jogadorId}")
    public ResponseEntity lancarJogadaPedra(@PathVariable("jogadorId") String jogadorId) 
    throws BusinessException, ResourceNotFoundException {
        commandService.lancarJogadaPedra(jogadorId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/tesoura/jogador/{jogadorId}")
    public ResponseEntity lancarJogadaTesoura(@PathVariable("jogadorId") String jogadorId) 
    throws BusinessException, ResourceNotFoundException {
        commandService.lancarJogadaTesoura(jogadorId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/spock/jogador/{jogadorId}")
    public ResponseEntity lancarJogadaSpock(@PathVariable("jogadorId") String jogadorId) 
    throws BusinessException, ResourceNotFoundException {
        commandService.lancarJogadaSpock(jogadorId);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/lagarto/jogador/{jogadorId}")
    public ResponseEntity lancarJogadaLagarto(@PathVariable("jogadorId") String jogadorId) 
    throws BusinessException, ResourceNotFoundException {
        commandService.lancarJogadaLagarto(jogadorId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/jogador/{jogadorId}")
    public ResponseEntity removerJogada(@PathVariable("jogadorId") String jogadorId) 
    throws BusinessException, ResourceNotFoundException {
        commandService.removerJogada(jogadorId);
        return ResponseEntity.ok().build();
    }

}
