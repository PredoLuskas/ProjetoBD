package com.project.shopanime.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioDTO {

    private Integer ano;
    private Integer mes;

    private Long quantidadeVendas;
    private BigDecimal totalVendas;

}
