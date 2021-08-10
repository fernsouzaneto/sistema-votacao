package com.southsystem.votacao.application.representation.mapper;

import com.southsystem.votacao.application.representation.PautaRepresentation;
import com.southsystem.votacao.domain.DAO.Pauta;

import java.time.LocalDateTime;

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
                .dtFim(dateTime.plusMinutes(setMinDuracao(pauta)))
                .minutosDuracao(pauta.getMinutosDuracao())
                .flagAtiva(pauta.getFlagAtiva())
                .descricao(pauta.getDescricao())
                .build();
    }

    private static Long setMinDuracao(PautaRepresentation pauta) {
        long minDuracaoDefault = 1L;
        if (pauta.getMinutosDuracao() == null) {
            pauta.setMinutosDuracao(minDuracaoDefault);
        }
        return pauta.getMinutosDuracao();
    }
}
