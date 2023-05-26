package com.fluxocaixa.enums;

public enum TipoLancamento {
    DEBITO("Débito"),
    CREDITO("Crédito");
    private String descricao;
    TipoLancamento(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
