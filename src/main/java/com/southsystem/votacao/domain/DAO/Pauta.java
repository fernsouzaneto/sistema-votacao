package com.southsystem.votacao.domain.DAO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PAUTA")
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CDPAUTA", nullable = false)
    Integer id;

    @Column(name = "DSPAUTA", length = 200, nullable = false)
    String descricao;

    @Column(name = "DTINICIO", nullable = false)
    LocalDateTime dtInicio;

    @Column(name = "MINDURACAO", nullable = false)
    Integer minutosDuracao;



}
