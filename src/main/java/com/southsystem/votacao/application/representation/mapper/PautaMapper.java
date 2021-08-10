package com.southsystem.votacao.application.representation.mapper;

import com.southsystem.votacao.application.representation.PautaRepresentation;
import com.southsystem.votacao.domain.DAO.Pauta;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;

public class PautaMapper {

    public static PautaRepresentation toRepresentation(Pauta pauta) {
        return PautaRepresentation.builder()
                .id(pauta.getId())
                .dtInicio(pauta.getDtInicio())
                .dtFim(pauta.getDtFim())
                .minutosDuracao(pauta.getMinutosDuracao())
                .flagAtiva(pauta.getFlagAtiva())
                .descricao(pauta.getDescricao())
                .build();
    }

    public static Pauta fromRepresentation(PautaRepresentation pauta) {
        LocalDateTime dateTime = LocalDateTime.now();
        return Pauta.builder()
                .id(pauta.getId())
                .dtInicio(dateTime)
                .dtFim(dateTime.plusMinutes(pauta.getMinutosDuracao()))
                .minutosDuracao(pauta.getMinutosDuracao())
                .flagAtiva(pauta.getFlagAtiva())
                .descricao(pauta.getDescricao())
                .build();
    }
}
