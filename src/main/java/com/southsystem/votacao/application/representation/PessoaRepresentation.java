package com.southsystem.votacao.application.representation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaRepresentation {
    Long id;
    String nmPessoa;
    String nuCpfCnpj;
}
