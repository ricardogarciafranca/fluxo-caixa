package com.fluxocaixa.service;



import com.fluxocaixa.dto.LancamentoDTO;

import java.util.List;

public interface LancamentoService {

    LancamentoDTO criarLancamento(LancamentoDTO lancamentoDTO);

    List<LancamentoDTO> obterLancamentos();
}
