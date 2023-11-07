package com.project.shopanime.model.vendas;

import com.project.shopanime.model.produtos.Produto;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CompraRequest {

    private Long idCliente;
    private Long idProdutos;
    private Long idVendedor;
    private String formaPagamento;
    private List<Long> idItem;
}