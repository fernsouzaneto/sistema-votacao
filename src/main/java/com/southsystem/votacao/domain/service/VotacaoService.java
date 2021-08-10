package com.southsystem.votacao.domain.service;

import com.southsystem.votacao.domain.DAO.Pauta;
import com.southsystem.votacao.domain.DAO.Pessoa;
import com.southsystem.votacao.domain.DAO.Votacao;
import com.southsystem.votacao.domain.exception.VotacaoException;
import com.southsystem.votacao.domain.repository.PautaRepository;
import com.southsystem.votacao.domain.repository.PessoaRepository;
import com.southsystem.votacao.domain.repository.VotacaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class VotacaoService {

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private VotacaoRepository votacaoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pauta criarPauta(Pauta pauta) {
        log.info("Criando pauta ->", pauta);
        Pauta pautaCrida = pautaRepository.save(pauta);
        pautaCrida.setMinutosDuracao(pauta.getMinutosDuracao());
        log.info("Pauta Criada com sucesso!  ->", pautaCrida);
        return pautaCrida;
    }

    public List<Votacao> listarResultado(Long id) {
        log.info("Listar resultado da pauta ", id);
        return votacaoRepository.consultarPorPauta(id);
    }

    public List<Pauta> listarPautas(){
        return pautaRepository.findAll();
    }

    public Votacao votar(String nuCPFCNPJ, String voto, Long cdPauta) throws Exception {

        Optional<Pauta> pauta = pautaRepository.findById(cdPauta);
        Optional<Pessoa> pessoa = pessoaRepository.findByCPFCNPJ(nuCPFCNPJ);

        if (pauta.isPresent() && pessoa.isPresent()) {
            verificarDuplicidadeDoVoto(pauta.get(), pessoa.get());
            verificarDisponibilidadeDaPauta(pauta.get());
        } else {
            if (pauta.isEmpty()) {
                log.error("Pauta {} não encontrada!", cdPauta);
                throw new VotacaoException("Pauta não encontrada!");
            }
            log.error("Pessoa do CPF/CNPJ {} não encontrada!",nuCPFCNPJ);
            throw new VotacaoException("Pessoa não encontrada!");
        }

        Votacao votacao = Votacao.builder()
                .pauta(pauta.get())
                .voto(voto)
                .pessoa(pessoa.get())
                .build();

        return votacaoRepository.save(votacao);
    }

    private void verificarDisponibilidadeDaPauta(Pauta pauta) throws Exception {
        if ("N".equals(pauta.getFlagAtiva())) {
            log.error("Tempo da sessão da pauta {} expirou", pauta);
            throw new VotacaoException("Sessao de votação expirada.");
        } else if (LocalDateTime.now().isAfter(pauta.getDtFim())) {
            pauta.setFlagAtiva("N");
            log.error("Tempo da sessão da pauta {} expirou", pauta);
            pautaRepository.save(pauta);
            throw new VotacaoException("Sessao de votação expirada.");
        }
    }

    private void verificarDuplicidadeDoVoto(Pauta pauta, Pessoa pessoa) throws Exception {
        Optional<Votacao> votacao = votacaoRepository.consultarVotoDuplicado(pessoa.getId(), pauta.getId());
        if (votacao.isPresent()) {
            log.error("Tentativa de votaçao duplicada para a pauta {}", pauta.getId());
            throw new VotacaoException("Tentativa de votaçao duplicada");
        }
    }
}
