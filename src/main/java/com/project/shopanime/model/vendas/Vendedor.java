package com.project.shopanime.model.vendas;

import jakarta.annotation.Nullable;
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
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    // Outros campos de informações do vendedor

/*    @Nullable
    @OneToMany
    @JoinColumn(name = "vendedor")
    private List<Compra> compras = new ArrayList<>();*/
    // Getters e setters
}
