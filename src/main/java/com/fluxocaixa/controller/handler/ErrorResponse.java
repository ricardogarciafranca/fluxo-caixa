package com.fluxocaixa.controller.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class ErrorResponse {
    private int code;
    private String message;
}
