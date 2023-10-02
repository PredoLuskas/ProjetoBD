package com.project.shopanime.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ArtFigure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String anime;
    private String personagem;
    private String tamanho;
    Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

}
