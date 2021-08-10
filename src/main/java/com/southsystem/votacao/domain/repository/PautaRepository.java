package com.southsystem.votacao.domain.repository;

import com.southsystem.votacao.domain.DAO.Pauta;
import com.southsystem.votacao.domain.DAO.Pessoa;
import com.southsystem.votacao.domain.DAO.Votacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
