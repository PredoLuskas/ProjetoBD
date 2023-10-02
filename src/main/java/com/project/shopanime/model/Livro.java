package com.project.shopanime.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    Integer quantidade;
    @ManyToOne
    private Produto produto;
    private String nome;
    @Column(name = "quantpag")
    private int quantPag;
    private String author;
    private String idioma;
}
