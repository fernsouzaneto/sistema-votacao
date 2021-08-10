package com.southsystem.votacao.application.representation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PautaRepresentation {
    Long id;
    String descricao;
    LocalDateTime dtInicio;
    LocalDateTime dtFim;
    Long minutosDuracao;
    String flagAtiva;
}
