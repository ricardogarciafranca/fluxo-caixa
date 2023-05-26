package com.fluxocaixa.controller;

import com.fluxocaixa.dto.ConsolidadoDTO;
import com.fluxocaixa.service.ConsolidadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/consolidado")
@Api(tags = "Consolidado Diário")
public class ConsolidadoController {

    private final ConsolidadoService consolidadoService;

    public ConsolidadoController(ConsolidadoService consolidadoService) {
        this.consolidadoService = consolidadoService;
    }

    @GetMapping
    @ApiOperation("Obtém o saldo diário consolidado")
    public ResponseEntity<ConsolidadoDTO> obterSaldoConsolidado(
            @Parameter(description = "data", example = "2023-05-26")
            @RequestParam(name = "date", required = true)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        ConsolidadoDTO saldoConsolidado = consolidadoService.obterSaldoConsolidado(date);
        return ResponseEntity.ok(saldoConsolidado);
    }
}
