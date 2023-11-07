package com.project.shopanime.utils;

public enum TipoProduto {
    ART_FIGURE(1),
    LIVRO(2),
    ROUPA(3);

    private final int value;

    TipoProduto(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
