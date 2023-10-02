package com.project.shopanime.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO {

    Long id;
    Integer quantidade;
    private String nome;
    private int quantPag;
    private String author;
    private String idioma;
}