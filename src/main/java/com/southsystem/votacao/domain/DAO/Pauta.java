package com.southsystem.votacao.domain.DAO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PAUTA")
public class Pauta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CDPAUTA", nullable = false)
    Long id;

    @Column(name = "DSPAUTA", length = 200, nullable = false)
    String descricao;

    @Column(name = "DTINICIO", nullable = false)
    LocalDateTime dtInicio;

    @Column(name = "DTFIM", nullable = false)
    LocalDateTime dtFim;

    @Column(name = "FLATIVA", length = 1)
    String flagAtiva;

    @Transient
    Long minutosDuracao;
}
