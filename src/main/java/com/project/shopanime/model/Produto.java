package com.project.shopanime.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Boolean disponibilidade;
    BigDecimal preco;
    String descricao;
//    Integer quantidade;

/*    @OneToMany(mappedBy = "produto")
    List<Roupa> roupa;*/

}
