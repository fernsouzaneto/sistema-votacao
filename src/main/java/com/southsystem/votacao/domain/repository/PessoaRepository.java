package com.southsystem.votacao.domain.repository;

import com.southsystem.votacao.domain.DAO.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
