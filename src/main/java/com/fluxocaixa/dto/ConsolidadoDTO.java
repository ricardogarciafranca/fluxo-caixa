package com.fluxocaixa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsolidadoDTO {
    private LocalDate data;
    private BigDecimal inicio;
    private List<LancamentoDTO> lancamentos;
    private BigDecimal fim;
}
