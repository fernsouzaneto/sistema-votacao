package com.southsystem.votacao.application.representation;

import lombok.Data;

@Data
public class ErrorResponse {
    String message;
    int code;
}
