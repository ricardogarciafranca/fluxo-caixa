package com.fluxocaixa.service.impl;

import com.fluxocaixa.dto.ConsolidadoDTO;
import com.fluxocaixa.dto.LancamentoDTO;
import com.fluxocaixa.repository.LancamentoRepository;
import com.fluxocaixa.service.ConsolidadoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsolidadoServiceImpl implements ConsolidadoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ConsolidadoDTO obterSaldoConsolidado(LocalDate date) {
        LocalDateTime begin = LocalDateTime.of(date, LocalTime.of(0, 0));
        LocalDateTime end = LocalDateTime.of(date, LocalTime.of(23, 59));

        List<LancamentoDTO> lancamentos = lancamentoRepository.findAllByDateTimeBetweenOrderByDateTimeAsc(begin, end).stream()
                .map(lancamento -> modelMapper.map(lancamento, LancamentoDTO.class))
                .collect(Collectors.toList());

        return ConsolidadoDTO.builder().data(date)
                .inicio(lancamentos.get(0).getConsolidado())
                .lancamentos(lancamentos)
                .fim(lancamentos.get(lancamentos.size() - 1).getConsolidado()).build();
    }

}
