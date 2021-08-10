package com.southsystem.votacao.application.representation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resultado {
    long qtdSim;
    long qtdNao;
    List<VotacaoRepresentation> listaResultado;

}
