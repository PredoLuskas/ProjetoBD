package com.project.shopanime.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoupaDTO {

    private Long produtoId;
    @Column(name = "tipovestimenta")
    private String tipovestimenta;
    private String tamanho;
    private String cor;
    private Integer quantidade;

}
