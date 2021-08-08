package com.southsystem.votacao.domain.service;

import com.southsystem.votacao.domain.DAO.Pauta;
import com.southsystem.votacao.domain.DAO.Pessoa;
import com.southsystem.votacao.domain.DAO.Votacao;
import com.southsystem.votacao.domain.repository.PautaRepository;
import com.southsystem.votacao.domain.repository.PessoaRepository;
import com.southsystem.votacao.domain.repository.VotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VotacaoService {

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private VotacaoRepository votacaoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pauta inicarNovaPauta(Pauta pauta) {
        Pauta pautaCrida = pautaRepository.save(pauta);
        pautaCrida.setMinutosDuracao(pauta.getMinutosDuracao());

        return  pautaCrida;
    }

    public List<Votacao> listarResultado(Long id) {
        return votacaoRepository.consultarPorPauta(id);
    }

    public void votar(String nuCPFCNPJ, String voto, Long cdPauta) throws Exception {

        Optional<Pauta> pauta = pautaRepository.findById(cdPauta);
        Optional<Pessoa> pessoa = pessoaRepository.findByCPFCNPJ(nuCPFCNPJ);

        if(pauta.isPresent() && pessoa.isPresent()){
            verificaDadosExistentes(pauta.get(), pessoa.get());
            verificaPautaDisponivel(pauta.get());
        }else{
            if (pauta.isEmpty()) {
                throw new Exception("Pauta não encontrada!");
            }
            throw new Exception("Pessoa não encontrada!");
        }

        Votacao votacao = Votacao.builder()
                .pauta(pauta.get())
                .voto(voto)
                .pessoa(pessoa.get())
                .build();

        votacaoRepository.save(votacao);
    }

    private void verificaPautaDisponivel(Pauta pauta) throws Exception {
        if("N".equals(pauta.getFlagAtiva()) ){
            throw new Exception("Sessao finalizada para votos.");
        }else if(LocalDateTime.now().isAfter(pauta.getDtFim())){
            pauta.setFlagAtiva("N");
            pautaRepository.save(pauta);
            throw new Exception("Sessao finalizada para votos.");
        }
    }

    private void verificaDadosExistentes(Pauta pauta, Pessoa pessoa) throws Exception {
        Optional<Votacao> votacao = votacaoRepository.consultarVotoDuplicado(pessoa.getId(), pauta.getId());
        if (votacao.isPresent()) {
            throw new Exception("Tentativa de votaçao duplicada");
        }

    }
}
