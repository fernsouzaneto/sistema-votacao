package com.southsystem.votacao.domain;

import com.southsystem.votacao.application.representation.TipoVoto;
import com.southsystem.votacao.domain.DAO.Pauta;
import com.southsystem.votacao.domain.DAO.Pessoa;
import com.southsystem.votacao.domain.DAO.Votacao;
import com.southsystem.votacao.domain.exception.VotacaoException;
import com.southsystem.votacao.domain.repository.PautaRepository;
import com.southsystem.votacao.domain.repository.PessoaRepository;
import com.southsystem.votacao.domain.repository.VotacaoRepository;
import com.southsystem.votacao.domain.service.VotacaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class VotacaoServiceTests {


    @Mock
    PautaRepository pautaRepository;

    @Mock
    VotacaoRepository votacaoRepository;

    @Mock
    PessoaRepository pessoaRepository;

    @InjectMocks
    VotacaoService service;


    @Test
    void criar_pauta_com_sucesso() {
        Pauta pautaMock = Pauta.builder()
                .id(1L)
                .descricao("desc")
                .dtFim(LocalDateTime.now())
                .dtInicio(LocalDateTime.now())
                .flagAtiva("S")
                .minutosDuracao(60L)
                .build();

        when(pautaRepository.save(any(Pauta.class)))
                .thenReturn(pautaMock);

        var sut = service.criarPauta(mock(Pauta.class));
        Assertions.assertEquals(1L, sut.getId());
    }

    @Test
    void listar_resultado_votacao_sucesso() {
        List<Votacao> votacaoList = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            Votacao votacaoMock = Votacao.builder()
                    .pauta(Pauta.builder().id(1L).build())
                    .pessoa(Pessoa.builder().nuCpfCnpj("123").build())
                    .voto(TipoVoto.SIM.name())
                    .build();
            votacaoList.add(votacaoMock);
        }

        when(votacaoRepository.consultarPorPauta(1L))
                .thenReturn(votacaoList);

        var sut = service.listarResultado(1L);
        Assertions.assertFalse(sut.isEmpty());
        Assertions.assertEquals(6, sut.size());
    }

    @Test
    void votar_em_pauta_existente_e_pessoa_existente() throws Exception {
        Pauta pauta = Pauta.builder()
                .id(1L)
                .descricao("desc")
                .dtFim(LocalDateTime.now().plusMinutes(60))
                .dtInicio(LocalDateTime.now())
                .flagAtiva("S")
                .minutosDuracao(60L)
                .build();
        Pessoa pessoa = Pessoa.builder()
                .nuCpfCnpj("123")
                .nmPessoa("test")
                .id(1L)
                .build();

        Votacao votacao = Votacao.builder()
                .id(1L)
                .build();

        when(pautaRepository.findById(1L))
                .thenReturn(Optional.of(pauta));

        when(pessoaRepository.findByCPFCNPJ("123"))
                .thenReturn(Optional.of(pessoa));

        when(votacaoRepository.consultarVotoDuplicado(1L,1L))
                .thenReturn(Optional.empty());

        when(votacaoRepository.save(any(Votacao.class))).thenReturn(votacao);

        var sut = service.votar("123",TipoVoto.SIM.name(),1L);
        Assertions.assertEquals(1L, sut.getId());

    }

    @Test
    void votar_em_pauta_nao_existente_exception() throws Exception {


        Pessoa pessoa = Pessoa.builder()
                .nuCpfCnpj("123")
                .nmPessoa("test")
                .id(1L)
                .build();

        when(pautaRepository.findById(1L))
                .thenReturn(Optional.empty());

        when(pessoaRepository.findByCPFCNPJ("123"))
                .thenReturn(Optional.of(pessoa));

        Assertions.assertThrows(VotacaoException.class,
                () -> service.votar("123",TipoVoto.SIM.name(),1L));

    }

    @Test
    void votar_em_pauta_existente_com_pessoa_nao_existente_exception() throws Exception {

        Pauta pauta = Pauta.builder()
                .id(1L)
                .descricao("desc")
                .dtFim(LocalDateTime.now().plusMinutes(60))
                .dtInicio(LocalDateTime.now())
                .flagAtiva("S")
                .minutosDuracao(60L)
                .build();


        when(pautaRepository.findById(1L))
                .thenReturn(Optional.of(pauta));

        when(pessoaRepository.findByCPFCNPJ("123"))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(VotacaoException.class,
                () -> service.votar("123",TipoVoto.SIM.name(),1L));

    }


    @Test
    void votar_em_pauta_expirada_retorna_exception() throws Exception {
        Pauta pauta = Pauta.builder()
                .id(1L)
                .descricao("desc")
                .dtFim(LocalDateTime.now().minusDays(1))
                .dtInicio(LocalDateTime.now().minusDays(2))
                .flagAtiva("S")
                .minutosDuracao(60L)
                .build();
        Pessoa pessoa = Pessoa.builder()
                .nuCpfCnpj("123")
                .nmPessoa("test")
                .id(1L)
                .build();

        when(pautaRepository.findById(1L))
                .thenReturn(Optional.of(pauta));

        when(pessoaRepository.findByCPFCNPJ("123"))
                .thenReturn(Optional.of(pessoa));

        when(votacaoRepository.consultarVotoDuplicado(1L,1L))
                .thenReturn(Optional.empty());

        when(pautaRepository.save(any(Pauta.class)))
                .thenReturn(null);

        Assertions.assertThrows(VotacaoException.class,
                () -> service.votar("123",TipoVoto.SIM.name(),1L));

        pauta.setFlagAtiva("N");
        Assertions.assertThrows(VotacaoException.class,
                () -> service.votar("123",TipoVoto.SIM.name(),1L));
    }

    @Test
    void voto_ja_registrado_retorna_exception() throws Exception {
        Pauta pauta = Pauta.builder()
                .id(1L)
                .descricao("desc")
                .dtFim(LocalDateTime.now().plusMinutes(60))
                .dtInicio(LocalDateTime.now())
                .flagAtiva("S")
                .minutosDuracao(60L)
                .build();
        Pessoa pessoa = Pessoa.builder()
                .nuCpfCnpj("123")
                .nmPessoa("test")
                .id(1L)
                .build();

        Votacao votacao = Votacao.builder()
                .id(1L)
                .build();

        when(pautaRepository.findById(1L))
                .thenReturn(Optional.of(pauta));

        when(pessoaRepository.findByCPFCNPJ("123"))
                .thenReturn(Optional.of(pessoa));

        when(votacaoRepository.consultarVotoDuplicado(1L,1L))
                .thenReturn(Optional.of(votacao));

        when(votacaoRepository.save(any(Votacao.class))).thenReturn(votacao);

        Assertions.assertThrows(VotacaoException.class,
                () -> service.votar("123",TipoVoto.SIM.name(),1L));
    }

}
