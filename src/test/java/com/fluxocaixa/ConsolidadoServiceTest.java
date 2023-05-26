package com.fluxocaixa;

import com.fluxocaixa.dto.ConsolidadoDTO;
import com.fluxocaixa.entities.Lancamento;
import com.fluxocaixa.enums.TipoLancamento;
import com.fluxocaixa.repository.LancamentoRepository;
import com.fluxocaixa.service.ConsolidadoService;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = FluxoCaixaApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class ConsolidadoServiceTest {

    @Autowired
    private ConsolidadoService consolidadoService;

    @MockBean
    private LancamentoRepository lancamentoRepository;

    @Test
    public void obtemLancamentoSuccessTest() {
        List<Lancamento> mockLancamentos = createMockLancamentos();
        Mockito.when(lancamentoRepository.findAll()).thenReturn(mockLancamentos);
        Mockito.when(lancamentoRepository.findAllByDateTimeBetweenOrderByDateTimeAsc(Mockito.any(LocalDateTime.class), Mockito.any(LocalDateTime.class))).thenReturn(mockLancamentos);
        ConsolidadoDTO consolidado = consolidadoService.obterSaldoConsolidado(LocalDate.now());
        Assertions.assertEquals(consolidado.getInicio(), mockLancamentos.get(0).getConsolidado());
        Assertions.assertEquals(consolidado.getFim(), mockLancamentos.get(mockLancamentos.size() - 1).getConsolidado());
    }

    private Lancamento createMockLancamento(String id, double valor, TipoLancamento tipo, LocalDateTime dateTime) {
        Lancamento lancamento = new Lancamento();
        lancamento.setId(id);
        lancamento.setIdUsuario("1");
        lancamento.setDescricao(tipo.getDescricao());
        lancamento.setTipo(tipo);
        lancamento.setValor(valor);
        lancamento.setDateTime(dateTime);
        return lancamento;
    }

    private List<Lancamento> createMockLancamentos() {
        Lancamento lancamento1 = createMockLancamento("1", 10.1, TipoLancamento.CREDITO, LocalDateTime.now());
        Lancamento lancamento2 = createMockLancamento("2", 10.1, TipoLancamento.CREDITO, LocalDateTime.now().plusMinutes(1));
        Lancamento lancamento3 = createMockLancamento("3", 10.1, TipoLancamento.CREDITO, LocalDateTime.now().plusMinutes(2));
        Lancamento lancamento4 = createMockLancamento("4", 10.1, TipoLancamento.DEBITO, LocalDateTime.now().plusMinutes(3));
        Lancamento lancamento5 = createMockLancamento("5", 10.1, TipoLancamento.DEBITO, LocalDateTime.now().plusMinutes(4));
        return List.of(lancamento1, lancamento2, lancamento3, lancamento4, lancamento5);
    }

}
