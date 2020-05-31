package com.avaliacao.jokenpo.controller;

import com.avaliacao.jokenpo.application.commandService.PartidaCommandService;
import com.avaliacao.jokenpo.application.queryService.PartidaQueryService;
import com.avaliacao.jokenpo.helpers.BusinessException;
import com.avaliacao.jokenpo.queries.partida.PartidaResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/partidas")
public class PartidaController {

    private PartidaCommandService commandService;
    private PartidaQueryService queryService;

    public PartidaController(PartidaCommandService commandService,PartidaQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @GetMapping("/atual")
    public ResponseEntity<PartidaResult> obterPartidaAtual() {
        return ResponseEntity.ok(queryService.obterPartidaAtual());
    }
    
    @PostMapping("/jogar")
    public ResponseEntity<PartidaResult> jogar() throws BusinessException {
        return ResponseEntity.ok(commandService.jogar());
    }
    
    @PostMapping("/resetar")
    public ResponseEntity resetar() {
        commandService.resetarJogo();
        return ResponseEntity.ok().build();
    }

}
