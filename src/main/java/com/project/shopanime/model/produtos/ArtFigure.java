package com.project.shopanime.model.produtos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "artfigure")
public class ArtFigure {

    Integer quantidade;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String anime;
    private String personagem;
    private String tamanho;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

}
