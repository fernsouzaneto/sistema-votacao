package com.southsystem.votacao.application.controller;

import com.southsystem.votacao.application.representation.PautaRepresentation;
import com.southsystem.votacao.application.representation.mapper.PautaMapper;
import com.southsystem.votacao.domain.service.VotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class VotacaoController {

    @Autowired
    VotacaoService votacaoService;

    @PostMapping("/pauta/criar")
    public PautaRepresentation novaPauta(@RequestBody PautaRepresentation pauta){
        PautaRepresentation response
                = PautaMapper.toRepresentation(votacaoService.inicarNovaPauta(PautaMapper.fromRepresentation(pauta)));

        return response;
    }
}
