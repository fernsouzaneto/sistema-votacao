package com.southsystem.votacao.domain.DAO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_VOTACAO")
public class Votacao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CDVOTACAO", nullable = false)
    private Long id;

    @Column(name = "DSVOTO", nullable = false)
    private String voto;

    @ManyToOne
    @JoinColumn(name = "CDPAUTA")
    private Pauta pauta;

    @ManyToOne
    @JoinColumn(name = "CDPESSOA")
    private Pessoa pessoa;
}
