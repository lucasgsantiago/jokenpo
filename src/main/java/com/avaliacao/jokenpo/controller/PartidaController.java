package com.avaliacao.jokenpo.controller;

import com.avaliacao.jokenpo.helpers.BusinessException;
import com.avaliacao.jokenpo.queries.partida.PartidaResult;
import com.avaliacao.jokenpo.service.PartidaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/partida")
public class PartidaController {

    @Autowired
    private PartidaService service;

    @GetMapping("/atual")
    public ResponseEntity<PartidaResult> obterPartidaAtual() {
        return ResponseEntity.ok(service.obterPartidaAtual());
    }
    
    @PostMapping("/jogar")
    public ResponseEntity<PartidaResult> jogar() throws BusinessException {
        return ResponseEntity.ok(service.jogar());
    }

}
