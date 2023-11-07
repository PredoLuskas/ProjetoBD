package com.project.shopanime.model.produtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roupa")
public class Roupa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "tipovestimenta")
    String tipoVestimenta;

    String tamanho;
    String cor;
    Integer quantidade;

    @JoinColumn(name = "produto_id")
    @ManyToOne()
    Produto produto;

}
