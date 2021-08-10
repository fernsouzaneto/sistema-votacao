package com.southsystem.votacao.application.representation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotacaoRepresentation {
    Long id;
    Long cdPauta;
    TipoVoto voto;
    String nuCpfCnpj;
}
