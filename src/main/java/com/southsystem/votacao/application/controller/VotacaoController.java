package com.southsystem.votacao.application.controller;

import com.southsystem.votacao.application.representation.*;
import com.southsystem.votacao.application.representation.mapper.PautaMapper;
import com.southsystem.votacao.application.representation.mapper.PessoaMapper;
import com.southsystem.votacao.application.representation.mapper.VotacaoMapper;
import com.southsystem.votacao.domain.exception.VotacaoException;
import com.southsystem.votacao.domain.service.PessoaService;
import com.southsystem.votacao.domain.service.VotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VotacaoController {

    @Autowired
    VotacaoService votacaoService;

    @Autowired
    PessoaService pessoaService;

    @PostMapping("/pauta/criar")
    public PautaRepresentation novaPauta(@RequestBody PautaRepresentation pauta) {
        PautaRepresentation response
                = PautaMapper.toRepresentation(votacaoService.criarPauta(PautaMapper.fromRepresentation(pauta)));

        return response;
    }

    @PostMapping("/pauta/votar")
    public VotacaoRepresentation votar(@RequestParam Long cdPauta, @RequestParam String nuCPFCNPJ, @RequestParam TipoVoto voto) throws Exception {
        VotacaoRepresentation votocao = VotacaoMapper.toRepresentation(votacaoService.votar(nuCPFCNPJ, voto.name(), cdPauta));
        return votocao;
    }

    @GetMapping("/pauta/resultado/")
    public Resultado resultado(@RequestParam Long cdPauta) throws Exception {
        List<VotacaoRepresentation> listaVotos = VotacaoMapper.toRepresentationList(votacaoService.listarResultado(cdPauta));
        Resultado resultado = getResultado(listaVotos);
        return resultado;
    }


    @PostMapping("/pessoa/cadastrar")
    public PessoaRepresentation cadastrarPessoa(@RequestBody PessoaRepresentation pessoa) throws VotacaoException {
        PessoaRepresentation response
                = PessoaMapper.toRepresentation(pessoaService.cadastrar(PessoaMapper.fromRepresentation(pessoa)));

        return response;
    }

    @GetMapping("/pessoa/listar")
    public List<PessoaRepresentation> listarPessoas(){
        return PessoaMapper.toRepresentationList(pessoaService.listar());
    }

    private Resultado getResultado(List<VotacaoRepresentation> listaVotos) {
        return Resultado.builder()
                .listaResultado(listaVotos)
                .qtdNao(listaVotos.stream().filter(v -> TipoVoto.NAO.equals(v.getVoto())).count())
                .qtdSim(listaVotos.stream().filter(v -> TipoVoto.SIM.equals(v.getVoto())).count())
                .build();
    }
}
