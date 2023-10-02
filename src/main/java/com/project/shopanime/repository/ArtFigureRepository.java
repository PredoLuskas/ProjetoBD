package com.project.shopanime.repository;

import com.project.shopanime.model.ArtFigure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ArtFigureRepository extends JpaRepository<ArtFigure, Long> {

    // Buscar todas as figuras de arte
    List<ArtFigure> findAll();

    // Buscar uma figura de arte pelo ID
    Optional<ArtFigure> findById(Long id);

    // Inserir uma nova figura de arte
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO artfigure (anime,personagem,tamanho,quantidade,produto_id) VALUES (:anime, :personagem, :tamanho,:quantidade , :produtoId)", nativeQuery = true)
    int inserirArtFigure(@Param("anime") String anime, @Param("personagem") String personagem, @Param("tamanho") String tamanho, @Param("quantidade") Integer quantidade, @Param("produtoId") Long produtoId);
    ;

    // Excluir uma figura de arte pelo ID
    @Transactional
    @Modifying
    @Query("DELETE FROM ArtFigure af WHERE af.id = :id")
    void excluirArtFigurePorId(@Param("id") Long id);

    // Buscar figuras de arte por anime
    List<ArtFigure> findByAnime(String anime);

    // Buscar figuras de arte por personagem
    List<ArtFigure> findByPersonagem(String personagem);

    // Buscar figuras de arte por tamanho
    List<ArtFigure> findByTamanho(String tamanho);

    // Atualizar o anime de uma figura de arte pelo ID
    @Transactional
    @Modifying
    @Query("UPDATE ArtFigure af SET af.anime = :novoAnime WHERE af.id = :id")
    void atualizarAnimePorId(@Param("id") Long id, @Param("novoAnime") String novoAnime);

    // Atualizar o personagem de uma figura de arte pelo ID
    @Transactional
    @Modifying
    @Query("UPDATE ArtFigure af SET af.personagem = :novoPersonagem WHERE af.id = :id")
    void atualizarPersonagemPorId(@Param("id") Long id, @Param("novoPersonagem") String novoPersonagem);

    // Atualizar o tamanho de uma figura de arte pelo ID
    @Transactional
    @Modifying
    @Query("UPDATE ArtFigure af SET af.tamanho = :novoTamanho WHERE af.id = :id")
    void atualizarTamanhoPorId(@Param("id") Long id, @Param("novoTamanho") String novoTamanho);
}
