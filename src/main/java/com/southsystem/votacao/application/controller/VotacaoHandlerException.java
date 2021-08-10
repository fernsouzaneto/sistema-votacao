package com.southsystem.votacao.application.controller;

import com.southsystem.votacao.application.representation.ErrorResponse;
import com.southsystem.votacao.domain.exception.VotacaoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class VotacaoHandlerException {

    @ExceptionHandler(VotacaoException.class)
    public ResponseEntity<ErrorResponse> notFoundURLExceptionHandler(HttpServletRequest request, VotacaoException ex) {
        log.error("Request: " + request.getRequestURL() + " raised " + ex);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(ex.getErrorMessage());
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
