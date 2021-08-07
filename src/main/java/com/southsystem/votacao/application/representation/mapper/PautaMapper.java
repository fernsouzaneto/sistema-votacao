package com.southsystem.votacao.application.representation.mapper;

import com.southsystem.votacao.application.representation.PautaRepresentation;
import com.southsystem.votacao.domain.DAO.Pauta;

public class PautaMapper {

    public static PautaRepresentation toRepresentation(Pauta pauta){
        return PautaRepresentation.builder()
                .id(pauta.getId())
                .descricao(pauta.getDescricao())
                .build();
    }

    public static Pauta fromRepresentation(PautaRepresentation pauta){
        return Pauta.builder()
                .id(pauta.getId())
                .descricao(pauta.getDescricao())
                .build();
    }
}
