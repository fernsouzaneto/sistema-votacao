package com.southsystem.votacao.domain.DAO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_VOTACAO")
public class Votacao {
    Integer id;
    @ManyToOne
    Pauta pauta;
    Pessoa pessoa;
}
