package com.project.shopanime.repository.vendas;

import com.project.shopanime.model.vendas.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    Optional<Cliente> findByEndereco(String cidade);
    Optional<Cliente> findByNome(String nome);


/*    // Consulta personalizada para buscar um cliente por nome
    @Query("SELECT c FROM Cliente c WHERE c.nome = :nome")
    Optional<Cliente> findByNome(String nome);


    // Consulta personalizada para buscar todos os clientes em uma cidade específica
    @Query("SELECT c FROM Cliente c WHERE c.endereco = :cidade")
    List<Cliente> findByEndereco(String cidade)*/;

}
