package com.fluxocaixa;

import com.fluxocaixa.dto.LancamentoDTO;
import com.fluxocaixa.entities.Lancamento;
import com.fluxocaixa.enums.TipoLancamento;
import com.fluxocaixa.repository.LancamentoRepository;
import com.fluxocaixa.service.LancamentoService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = FluxoCaixaApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class LancamentoServiceTest {

    @Autowired
    private LancamentoService lancamentoService;

    @MockBean
    private LancamentoRepository lancamentoRepository;

    @Test
    public void criaLancamentoSuccessTest() {
        Mockito.when(lancamentoRepository.save(Mockito.any(Lancamento.class)))
                .thenReturn(createMockLancamento());
        LancamentoDTO lancamentoDTO = lancamentoService.criarLancamento(LancamentoDTO.builder().data(LocalDateTime.now())
                .tipo(TipoLancamento.CREDITO).valor(10.1).descricao(TipoLancamento.CREDITO.getDescricao()).build());
        Assertions.assertEquals(lancamentoDTO.getTipo(), TipoLancamento.CREDITO);
        Assertions.assertEquals(lancamentoDTO.getValor(), 10.1);
        Assertions.assertEquals(lancamentoDTO.getDescricao(), TipoLancamento.CREDITO.getDescricao());
        Assertions.assertEquals(lancamentoDTO.getId(), "1");
    }

    @Test
    public void obtemLancamentoSuccessTest() {
        Mockito.when(lancamentoRepository.findAll()).thenReturn(createMockLancamentos());
        List<LancamentoDTO> lancamentos = lancamentoService.obterLancamentos();
        Assertions.assertEquals(lancamentos.get(0).getTipo(), TipoLancamento.CREDITO);
        Assertions.assertEquals(lancamentos.get(0).getValor(), 10.1);
        Assertions.assertEquals(lancamentos.get(0).getDescricao(), TipoLancamento.CREDITO.getDescricao());
        Assertions.assertEquals(lancamentos.get(0).getId(), "1");
    }

    private Lancamento createMockLancamento() {
        Lancamento lancamento = new Lancamento();
        lancamento.setId("1");
        lancamento.setDescricao(TipoLancamento.CREDITO.getDescricao());
        lancamento.setTipo(TipoLancamento.CREDITO);
        lancamento.setValor(10.1);
        return lancamento;
    }

    private List<Lancamento> createMockLancamentos() {
        return List.of(createMockLancamento());
    }
}
