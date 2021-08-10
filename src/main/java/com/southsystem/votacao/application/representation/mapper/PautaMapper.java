package com.southsystem.votacao.application.representation.mapper;

import com.southsystem.votacao.application.representation.PautaRepresentation;
import com.southsystem.votacao.domain.DAO.Pauta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public static List<PautaRepresentation> toRepresentationList(List<Pauta> pautas){
        List<PautaRepresentation> representationList = new ArrayList<>();

        for(Pauta pauta : pautas){
            representationList.add(toRepresentation(pauta));
        }

        return representationList;
    }

    private static Long setMinDuracao(PautaRepresentation pauta) {
        long minDuracaoDefault = 1L;
        if (pauta.getMinutosDuracao() == null) {
            pauta.setMinutosDuracao(minDuracaoDefault);
        }
        return pauta.getMinutosDuracao();
    }
}
