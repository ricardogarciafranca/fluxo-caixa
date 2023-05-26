package com.fluxocaixa.service.impl;

import com.fluxocaixa.dto.LancamentoDTO;
import com.fluxocaixa.entities.Lancamento;
import com.fluxocaixa.entities.Usuario;
import com.fluxocaixa.enums.TipoLancamento;
import com.fluxocaixa.repository.LancamentoRepository;
import com.fluxocaixa.service.LancamentoService;
import com.fluxocaixa.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    private final LancamentoRepository lancamentoRepository;
    private final ModelMapper modelMapper;

    public LancamentoServiceImpl(LancamentoRepository lancamentoRepository, ModelMapper modelMapper) {
        this.lancamentoRepository = lancamentoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LancamentoDTO criarLancamento(LancamentoDTO lancamentoDTO) {
        Lancamento lancamento = modelMapper.map(lancamentoDTO, Lancamento.class);

        Optional<Lancamento> ultimoLancamento = lancamentoRepository.findFirstByIdUsuarioOrderByDateTimeDesc(lancamentoDTO.getIdUsuario());

        double ultimoValor = ultimoLancamento.map(Lancamento::getValor).orElse(0.0);

        double consolidado = lancamento.getTipo() == TipoLancamento.CREDITO ?
                (ultimoValor + lancamento.getValor()) :
                (ultimoValor - lancamento.getValor());

        lancamento.setConsolidado(new BigDecimal(consolidado));
        lancamento.setDateTime(LocalDateTime.now());
        lancamento.setIdUsuario("51da8d58-1643-4335-9485-4d8daf32c941");

        return modelMapper.map(lancamentoRepository.save(lancamento), LancamentoDTO.class) ;
    }

    @Override
    public List<LancamentoDTO> obterLancamentos() {
        List<Lancamento> lancamentos = lancamentoRepository.findAll();
        return lancamentos.stream()
                .map(lancamento -> modelMapper.map(lancamento, LancamentoDTO.class))
                .collect(Collectors.toList());
    }

}
