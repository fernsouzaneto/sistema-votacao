package com.southsystem.votacao.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VotacaoException extends Exception{
    private String errorMessage;
}
