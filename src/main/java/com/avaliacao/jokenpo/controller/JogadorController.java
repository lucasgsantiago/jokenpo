package com.avaliacao.jokenpo.controller;

import com.avaliacao.jokenpo.application.commandService.JogadorCommandService;
import com.avaliacao.jokenpo.application.queryService.JogadorQueryService;
import com.avaliacao.jokenpo.command.jogador.AdicinarJogadorCommand;
import com.avaliacao.jokenpo.helpers.BusinessException;
import com.avaliacao.jokenpo.helpers.ResourceNotFoundException;
import com.avaliacao.jokenpo.helpers.Success;
import com.avaliacao.jokenpo.queries.jogador.JogadorListResult;
import com.avaliacao.jokenpo.queries.jogador.JogadorResult;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/jogadores")
public class JogadorController {

    private JogadorCommandService commandService;
    private JogadorQueryService queryService;
   
    public JogadorController(JogadorCommandService commandService,JogadorQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @GetMapping
    public ResponseEntity<JogadorListResult> listar() {
        return ResponseEntity.ok(queryService.listarJogadores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogadorResult> obter(@PathVariable("id") String id)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(queryService.obterJogador(id));
    }

    @PostMapping
    public ResponseEntity adicionarJogador(@RequestBody AdicinarJogadorCommand command) throws BusinessException {
        String correlationId = commandService.adicionarJogador(command);
        return ResponseEntity.ok(new Success(correlationId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removerJogada(@PathVariable("id") String id) throws ResourceNotFoundException {
        commandService.removerJogador(id);
        return ResponseEntity.ok().build();
    }

}
