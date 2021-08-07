package com.southsystem.votacao.domain.DAO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PESSOA")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CDPESSOA", nullable = false)
    Integer id;

    @Column(name = "NMPESSOA", length = 200, nullable = false)
    String nmPessoa;

    @Column(name = "NUMCPFCNPJ", length = 14, nullable = false)
    String nuCpfCnpj;
}
