package com.project.shopanime.model.vendas;

import com.project.shopanime.model.produtos.ArtFigure;
import com.project.shopanime.model.produtos.Produto;
import com.project.shopanime.utils.TipoPagamento;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Compras")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "datacompra")
    private LocalDate dataCompra;

    @Column(name = "statuspagamento")
    private String statusPagamento;

    @Column(name = "formapagamento")
    private String formaPagamento;

    @Column(name = "valortotal")
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "idcliente")  // Coluna na tabela "Compra" que faz referência ao Cliente
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idvendedor")
    private Vendedor vendedor;

    @ManyToOne
    @JoinColumn(name = "produtoid")
    private Produto produtos;
    // Getters e setters
}
