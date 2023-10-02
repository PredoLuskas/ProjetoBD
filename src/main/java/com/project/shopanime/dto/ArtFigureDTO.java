package com.project.shopanime.dto;

import com.project.shopanime.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtFigureDTO {

    private long id;
    private String anime;
    private String personagem;
    private String tamanho;
    Integer quantidade;

}

