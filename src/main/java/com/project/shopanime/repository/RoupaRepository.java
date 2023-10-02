package com.project.shopanime.repository;

import com.project.shopanime.model.Roupa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface RoupaRepository extends JpaRepository<Roupa, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO roupa (tipovestimenta, tamanho, cor,quantidade, produto_id) VALUES (:tipo, :tamanho, :cor,:quantidade,:produtoId)", nativeQuery = true)
    int inserirRoupa(@Param("tipo") String tipo, @Param("tamanho") String tamanho, @Param("cor") String cor, @Param("quantidade") Integer quantidade, @Param("produtoId") Long produtoId);
;

    // Excluir uma roupa pelo ID
    @Transactional
    @Modifying
    @Query("DELETE FROM Roupa r WHERE r.id = :id")
    void excluirRoupaPorId(@Param("id") Long id);

    // Buscar roupas por tipo de vestimenta
    List<Roupa> findByTipoVestimenta(String tipoVestimenta);

    // Buscar roupas por tamanho
    List<Roupa> findByTamanho(String tamanho);

    // Buscar roupas por cor
    List<Roupa> findByCor(String cor);

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
}
