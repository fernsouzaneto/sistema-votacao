package com.southsystem.votacao.domain.DAO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_VOTACAO")
public class Votacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CDVOTACAO", nullable = false)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "FKPAUTA", referencedColumnName = "CDPAUTA")
    Pauta pauta;

    @ManyToOne
    @JoinColumn(name = "FKPESSOA", referencedColumnName = "CDPESSOA")
    Pessoa pessoa;
}
