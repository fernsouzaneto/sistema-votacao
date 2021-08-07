package com.southsystem.votacao.domain.service;

import com.southsystem.votacao.domain.DAO.Pauta;
import com.southsystem.votacao.domain.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotacaoService {

    @Autowired
    private PautaRepository pautaRepository;

    public Pauta inicarNovaPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }
}
