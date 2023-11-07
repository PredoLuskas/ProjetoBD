package com.project.shopanime.model.vendas;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String endereco;

    @Column(name = "faonepiece")
    private boolean faOnePiece;
    @Column(name = "faflamengo")
    private boolean faFlamengo;
    // Outros campos de informações do cliente

/*    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras = new ArrayList<>()*/;

    // Getters e setters
}
