package com.project.shopanime.repository.vendas;

import com.project.shopanime.model.vendas.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

    @Query("SELECT v FROM Vendedor v WHERE v.nome = :nome")
    Vendedor findByNome(String nome);

}
