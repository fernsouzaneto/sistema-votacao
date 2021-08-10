package com.southsystem.votacao.application.representation.mapper;

import com.southsystem.votacao.application.representation.PautaRepresentation;
import com.southsystem.votacao.application.representation.PessoaRepresentation;
import com.southsystem.votacao.domain.DAO.Pauta;
import com.southsystem.votacao.domain.DAO.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class PessoaMapper {

    public static PessoaRepresentation toRepresentation(Pessoa pessoa){
        return PessoaRepresentation.builder()
                .id(pessoa.getId())
                .nmPessoa(pessoa.getNmPessoa())
                .nuCpfCnpj(pessoa.getNuCpfCnpj())
                .build();
    }

    public static Pessoa fromRepresentation(PessoaRepresentation pessoa){
        return Pessoa.builder()
                .id(pessoa.getId())
                .nmPessoa(pessoa.getNmPessoa())
                .nuCpfCnpj(pessoa.getNuCpfCnpj())
                .build();
    }

    public static List<PessoaRepresentation> toRepresentationList(List<Pessoa> pessoas){
        List<PessoaRepresentation> representationList = new ArrayList<>();
        for(Pessoa pessoa : pessoas){
            representationList.add(toRepresentation(pessoa));
        }

        return representationList;
    }
}
