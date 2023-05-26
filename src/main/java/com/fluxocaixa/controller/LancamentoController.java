package com.fluxocaixa.controller;

import com.fluxocaixa.dto.LancamentoDTO;
import com.fluxocaixa.service.LancamentoService;
import com.fluxocaixa.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
@Api(tags = "Lançamentos")
public class LancamentoController {

    private final LancamentoService lancamentoService;

    public LancamentoController(LancamentoService lancamentoService) {
        this.lancamentoService = lancamentoService;
    }

    @PostMapping
    @ApiOperation("Cria um novo lançamento")
    public ResponseEntity<Void> criarLancamento(@Valid @RequestBody LancamentoDTO lancamentoDTO) {

        lancamentoService.criarLancamento(lancamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @ApiOperation("Obtém a lista de lançamentos")
    public ResponseEntity<List<LancamentoDTO>> obterLancamentos() {

        List<LancamentoDTO> lancamentos = lancamentoService.obterLancamentos();
        return ResponseEntity.ok(lancamentos);
    }

}
