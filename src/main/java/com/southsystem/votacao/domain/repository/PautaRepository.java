package com.southsystem.votacao.domain.repository;

import com.southsystem.votacao.domain.DAO.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
}