package com.project.shopanime.repository;

import com.project.shopanime.model.Produto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p FROM Produto p")
    List<Produto> buscarTodosProdutos();

    @Query("SELECT p FROM Produto p WHERE p.disponibilidade = true")
    List<Produto> buscarProdutosDisponiveis();

    @Transactional//para garantir que a transação seja tratada corretamente.
    @Modifying //Para informar ao sgbd que está adicionando ou deletando no banco
    @Query("DELETE FROM Produto p WHERE p.id = ?1")
    void excluirProdutoPorId(Long id);

    @Query("SELECT p FROM Produto p WHERE p.id = ?1")
    Optional<Produto> buscarProdutoPorId(Long id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO produto (descricao, disponibilidade, preco, quantidade) " + "VALUES (:descricao, :disponibilidade, :preco, :quantidade)", nativeQuery = true)
    <S extends Produto> S salvarProduto(@Param("descricao") String descricao, @Param("disponibilidade") Boolean disponibilidade, @Param("preco") BigDecimal preco, @Param("quantidade") String quantidade);
}
