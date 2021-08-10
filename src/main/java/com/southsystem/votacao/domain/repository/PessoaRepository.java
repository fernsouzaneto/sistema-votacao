package com.southsystem.votacao.domain.repository;

import com.southsystem.votacao.domain.DAO.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query(value = "SELECT * from TB_PESSOA where NUMCPFCNPJ = :nuCPFCNPJ", nativeQuery = true)
    Optional<Pessoa> findByCPFCNPJ(@Param("nuCPFCNPJ") String nuCPFCNPJ);
}
