package com.project.shopanime.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@IdClass(RelatorioVendasMensalId.class)
@Getter
@Setter
public class RelatorioVendasMensal {
    @Id
    private Integer ano;

    @Id
    private Integer mes;

    private Long quantidadeVendas;
    private BigDecimal totalVendas;

    // Getters e setters
}
