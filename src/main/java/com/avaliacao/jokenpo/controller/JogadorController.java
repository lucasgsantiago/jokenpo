package com.avaliacao.jokenpo.controller;

import com.avaliacao.jokenpo.command.jogador.AdicinarJogadorCommand;
import com.avaliacao.jokenpo.helpers.BusinessException;
import com.avaliacao.jokenpo.helpers.Success;
import com.avaliacao.jokenpo.queries.jogador.JogadorListResult;
import com.avaliacao.jokenpo.queries.jogador.JogadorResult;
import com.avaliacao.jokenpo.service.JogadorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/jogador")
public class JogadorController {

    @Autowired
    private JogadorService service;

    @GetMapping
    public ResponseEntity<JogadorListResult> listar() {        
        return ResponseEntity.ok(service.listarJogadores());
    }

    @GetMapping("/{jogadorId}")
    public ResponseEntity<JogadorResult> obter(@PathVariable("jogadorId") String jogadorId) throws BusinessException {
        return ResponseEntity.ok(service.obterJogador(jogadorId));
    }

    @PostMapping
    public ResponseEntity adicionarJogador(@RequestBody AdicinarJogadorCommand command) throws BusinessException {
        String correlationId = service.adicionarJogador(command);
        return ResponseEntity.ok(new Success(correlationId));
    }

    @DeleteMapping("/{jogadorId}")
    public ResponseEntity removerJogada(@PathVariable("jogadorId") String jogadorId) throws BusinessException {
        service.removerJogador(jogadorId);
        return ResponseEntity.ok().build();
    }

}
