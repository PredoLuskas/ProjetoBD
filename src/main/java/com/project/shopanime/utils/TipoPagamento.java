package com.project.shopanime.utils;

import lombok.Getter;

@Getter
public enum TipoPagamento {
    CARTAO_CREDITO(1),
    CARTAO_DEBITO(2),
    BOLETO(3),
    PIX(4),
    BERRIES(5);

    private final int value;

    TipoPagamento(int value) {
        this.value = value;
    }

}
