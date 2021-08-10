package com.southsystem.votacao.application.representation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Long minutosDuracao;
    String flagAtiva;
}
