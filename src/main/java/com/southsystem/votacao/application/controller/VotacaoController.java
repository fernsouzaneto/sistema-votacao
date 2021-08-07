package com.southsystem.votacao.application.controller;

import com.southsystem.votacao.application.representation.PautaRepresentation;
import com.southsystem.votacao.application.representation.PessoaRepresentation;
import com.southsystem.votacao.application.representation.mapper.PautaMapper;
import com.southsystem.votacao.application.representation.mapper.PessoaMapper;
import com.southsystem.votacao.domain.service.PessoaService;
import com.southsystem.votacao.domain.service.VotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VotacaoController {

    @Autowired
    VotacaoService votacaoService;

    @Autowired
    PessoaService pessoaService;

    @PostMapping("/pauta/criar")
    public PautaRepresentation novaPauta(@RequestBody PautaRepresentation pauta) {
        PautaRepresentation response
                = PautaMapper.toRepresentation(votacaoService.inicarNovaPauta(PautaMapper.fromRepresentation(pauta)));

        return response;
    }

    @PostMapping("/pessoa/cadastrar")
    public PessoaRepresentation cadastrarPessoa(@RequestBody PessoaRepresentation pessoa) {
        PessoaRepresentation response
                = PessoaMapper.toRepresentation(pessoaService.cadastrar(PessoaMapper.fromRepresentation(pessoa)));

        return response;
    }
}
