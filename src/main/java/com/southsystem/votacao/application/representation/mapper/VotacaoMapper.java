package com.southsystem.votacao.application.representation.mapper;

import com.southsystem.votacao.application.representation.TipoVoto;
import com.southsystem.votacao.application.representation.VotacaoRepresentation;
import com.southsystem.votacao.domain.DAO.Votacao;

import java.util.ArrayList;
import java.util.List;


public class VotacaoMapper {

    public static VotacaoRepresentation toRepresentation(Votacao votacao) {
        return VotacaoRepresentation.builder()
                .cdPauta(votacao.getPauta().getId())
                .nuCpfCnpj(votacao.getPessoa().getNuCpfCnpj())
                .voto(TipoVoto.valueOf(votacao.getVoto()))
                .build();
    }

    public static List<VotacaoRepresentation> toRepresentationList(List<Votacao> votacaoList) {
        List<VotacaoRepresentation> representationList = new ArrayList<>();
        for (Votacao votacao : votacaoList) {
            representationList.add(toRepresentation(votacao));
        }

        return representationList;
    }
}
