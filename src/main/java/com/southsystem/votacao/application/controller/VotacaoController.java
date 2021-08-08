package com.southsystem.votacao.application.controller;

import com.southsystem.votacao.application.representation.*;
import com.southsystem.votacao.application.representation.mapper.PautaMapper;
import com.southsystem.votacao.application.representation.mapper.PessoaMapper;
import com.southsystem.votacao.application.representation.mapper.VotacaoMapper;
import com.southsystem.votacao.domain.DAO.Votacao;
import com.southsystem.votacao.domain.service.PessoaService;
import com.southsystem.votacao.domain.service.VotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
                = PautaMapper.toRepresentation(votacaoService.inicarNovaPauta(PautaMapper.fromRepresentation(pauta)));

        return response;
    }

    @PostMapping("/pauta/votar")
    public ResponseEntity<Object> novaPauta(@RequestParam Long cdPauta, @RequestParam String nuCPFCNPJ, @RequestParam TipoVoto voto) throws Exception {
        String sucesso = "Voto realizado com sucesso!!";
        votacaoService.votar(nuCPFCNPJ,voto.name(),cdPauta);
        return ResponseEntity.ok(sucesso);
    }

    @PostMapping("/pauta/resultado/")
    public Resultado resultado(@RequestParam Long cdPauta) throws Exception {
        List<VotacaoRepresentation> listaVotos = VotacaoMapper.toRepresentationList(votacaoService.listarResultado(cdPauta));
        Resultado resultado = Resultado.builder()
                .listaResultado(listaVotos)
                .qtdNao(listaVotos.stream().filter( v -> TipoVoto.NAO.equals(v.getVoto())).count())
                .qtdSim(listaVotos.stream().filter( v -> TipoVoto.SIM.equals(v.getVoto())).count())
                .build();

        return resultado;
    }



    @PostMapping("/pessoa/cadastrar")
    public PessoaRepresentation cadastrarPessoa(@RequestBody PessoaRepresentation pessoa) {
        PessoaRepresentation response
                = PessoaMapper.toRepresentation(pessoaService.cadastrar(PessoaMapper.fromRepresentation(pessoa)));

        return response;
    }
}
