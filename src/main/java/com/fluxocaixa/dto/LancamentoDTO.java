package com.fluxocaixa.dto;

import com.fluxocaixa.enums.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoDTO {

    private String id;

    private LocalDateTime data;

    @NotBlank(message = "descricao do lancamento obrigatorio")
    private String descricao;

    @NotNull(message = "valor de lancamento obrigatorio")
    private double valor;

    @NotNull(message = "tipo de lancamento obrigatorio")
    private TipoLancamento tipo;

    private BigDecimal consolidado;
    private String idUsuario;
}
