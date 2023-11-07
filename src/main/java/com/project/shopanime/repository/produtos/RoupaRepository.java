package com.project.shopanime.repository.produtos;

import com.project.shopanime.model.produtos.Livro;
import com.project.shopanime.model.produtos.Roupa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@EnableJpaRepositories
@Repository
public interface RoupaRepository extends JpaRepository<Roupa, Long> {
    @Query("SELECT r FROM Roupa r")
    List<Roupa> buscarTodos();
    @Query("SELECT r FROM Roupa r WHERE r.tipoVestimenta = :tipoVestimenta")
    List<Roupa> buscarPorVestimenta(String tipoVestimenta);

    @Query("SELECT r FROM Roupa r WHERE r.tamanho = :tamanho")
    List<Roupa> buscarPorTamanho(String tamanho);
    @Query("SELECT r FROM Roupa r WHERE r.id = :id")
    Roupa buscarPorId(Long id);
    // Buscar roupas por cor
    @Query("SELECT r FROM Roupa r WHERE r.cor = :cor")
    List<Roupa> buscarPorCor(String cor);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO roupa (tipovestimenta, tamanho, cor,quantidade, produto_id) VALUES (:tipo, :tamanho, :cor,:quantidade,:produtoId)", nativeQuery = true)
    int inserirRoupa(@Param("tipo") String tipo, @Param("tamanho") String tamanho, @Param("cor") String cor, @Param("quantidade") Integer quantidade, @Param("produtoId") Long produtoId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Roupa r WHERE r.id = :id")
    void excluirRoupaPorId(@Param("id") Long id);

    // Atualizar o tipo de vestimenta de uma roupa pelo ID
    @Transactional
    @Modifying
    @Query("UPDATE Roupa r SET r.tipoVestimenta = :novoTipoVestimenta WHERE r.id = :id")
    void atualizarTipoVestimentaPorId(@Param("id") Long id, @Param("novoTipoVestimenta") String novoTipoVestimenta);

    // Atualizar o tamanho de uma roupa pelo ID
    @Transactional
    @Modifying
    @Query("UPDATE Roupa r SET r.tamanho = :novoTamanho WHERE r.id = :id")
    void atualizarTamanhoPorId(@Param("id") Long id, @Param("novoTamanho") String novoTamanho);

    // Atualizar a cor de uma roupa pelo ID
    @Transactional
    @Modifying
    @Query("UPDATE Roupa r SET r.cor = :novaCor WHERE r.id = :id")
    void atualizarCorPorId(@Param("id") Long id, @Param("novaCor") String novaCor);

    @Query("SELECT l FROM Roupa l WHERE l.quantidade < 5")
    List<Roupa> busqueRoupasComMenosDeCincoQuant();

}
