package com.southsystem.votacao.domain.service;

import com.southsystem.votacao.domain.DAO.Pessoa;
import com.southsystem.votacao.domain.exception.VotacaoException;
import com.southsystem.votacao.domain.repository.PessoaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa cadastrar(Pessoa pessoa) throws VotacaoException {
        Optional<Pessoa> pessoaExistente = pessoaRepository.findByCPFCNPJ(pessoa.getNuCpfCnpj());

        if (pessoaExistente.isPresent()) {
            log.error("Pessoa já cadastrada -> {} ", pessoaExistente.get());
            throw new VotacaoException("Pessoa já cadastrada com CPF/CNPJ informado");
        }

        return pessoaRepository.save(pessoa);
    }
}
