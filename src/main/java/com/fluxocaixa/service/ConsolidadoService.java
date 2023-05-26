package com.fluxocaixa.service;

import com.fluxocaixa.dto.ConsolidadoDTO;

import java.time.LocalDate;

public interface ConsolidadoService {
    ConsolidadoDTO obterSaldoConsolidado(LocalDate date);
}
