package com.southsystem.votacao.application.controller;

import com.southsystem.votacao.application.representation.PautaRepresentation;
import com.southsystem.votacao.application.representation.PessoaRepresentation;
import com.southsystem.votacao.application.representation.TipoVoto;
import com.southsystem.votacao.domain.DAO.Pauta;
import com.southsystem.votacao.domain.DAO.Pessoa;
import com.southsystem.votacao.domain.DAO.Votacao;
import com.southsystem.votacao.domain.exception.VotacaoException;
import com.southsystem.votacao.domain.service.PessoaService;
import com.southsystem.votacao.domain.service.VotacaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class VotacaoControllerTests {
    @Mock
    VotacaoService votacaoService;

    @Mock
    PessoaService pessoaService;

    @InjectMocks
    VotacaoController controller;


    @Test
    void criar_pauta_com_sucesso_id_retornado() {
        Pauta pautaMock = Pauta.builder()
                .id(1L)
                .descricao("desc")
                .dtFim(LocalDateTime.now())
                .dtInicio(LocalDateTime.now())
                .flagAtiva("S")
                .minutosDuracao(60L)
                .build();

        when(votacaoService.criarPauta(any(Pauta.class)))
                .thenReturn(pautaMock);

        var sut = controller.novaPauta(mock(PautaRepresentation.class));
        Assertions.assertEquals(1L, sut.getId(), "Sucesso!");
    }

    @Test
    void votar_pauta_retorna_sucesso_id_retornado() throws Exception {
        Votacao votacaoMock = Votacao.builder()
                .id(1L)
                .pauta(Pauta.builder().id(1L).build())
                .pessoa(Pessoa.builder().nuCpfCnpj("123").build())
                .voto(TipoVoto.SIM.name())
                .build();

        when(votacaoService.votar("123", "SIM", 1L))
                .thenReturn(votacaoMock);

        var sut = controller.votar(1L, "123", TipoVoto.SIM);
        Assertions.assertEquals(1L, sut.getId());
    }

    @Test
    void consultar_resultado_votacao_retorna_lista() throws Exception {
        List<Votacao> votacaoList = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            Votacao votacaoMock = Votacao.builder()
                    .pauta(Pauta.builder().id(1L).build())
                    .pessoa(Pessoa.builder().nuCpfCnpj("123").build())
                    .voto(TipoVoto.SIM.name())
                    .build();
            votacaoList.add(votacaoMock);
        }

        when(votacaoService.listarResultado(1L))
                .thenReturn(votacaoList);
        var sut = controller.resultado(1L);
        Assertions.assertFalse(sut.getListaResultado().isEmpty());
        Assertions.assertEquals(6, sut.getListaResultado().size());
    }

    @Test
    void cadastro_da_pessoa_retorna_id_gerado() throws VotacaoException {
        PessoaRepresentation entrada= PessoaRepresentation.builder()
                .nmPessoa("test")
                .nuCpfCnpj("123")
                .build();

        Pessoa pessoa = Pessoa.builder()
                .id(1L)
                .build();

        when(pessoaService.cadastrar(any(Pessoa.class)))
                .thenReturn(pessoa);

        var sut = controller.cadastrarPessoa(entrada);
        Assertions.assertEquals(1L, sut.getId());
    }
}
