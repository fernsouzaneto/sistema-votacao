package com.southsystem.votacao.domain.repository;

import com.southsystem.votacao.domain.DAO.Votacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotacaoRepository  extends JpaRepository<Votacao, Long> {

    @Query(value = "SELECT TOP 1 * from TB_VOTACAO where CDPESSOA = :cdPessoa AND CDPAUTA = :cdPauta", nativeQuery = true)
    Optional<Votacao> consultarVotoDuplicado(@Param("cdPessoa") Long cdPessoa, @Param("cdPauta") Long cdPauta);

    @Query(value = "SELECT * from TB_VOTACAO where CDPAUTA = :cdPauta", nativeQuery = true)
    List<Votacao> consultarPorPauta(@Param("cdPauta") Long cdPauta);
}
