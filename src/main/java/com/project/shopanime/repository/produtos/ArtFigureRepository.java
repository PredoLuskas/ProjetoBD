package com.project.shopanime.repository.produtos;

import com.project.shopanime.model.produtos.ArtFigure;
import com.project.shopanime.model.produtos.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ArtFigureRepository extends JpaRepository<ArtFigure, Long> {
    @Query("SELECT af FROM ArtFigure af")
    List<ArtFigure> buscarTodos();


    @Query("SELECT af FROM ArtFigure af WHERE af.id = :id ")
    Optional<ArtFigure> buscarPorId(Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO artfigure (anime,personagem,tamanho,quantidade,produto_id) VALUES (:anime, :personagem, :tamanho,:quantidade , :produtoId)", nativeQuery = true)
    int inserirArtFigure(@Param("anime") String anime, @Param("personagem") String personagem, @Param("tamanho") String tamanho, @Param("quantidade") Integer quantidade, @Param("produtoId") Long produtoId);

    @Transactional
    @Modifying
    @Query("DELETE FROM ArtFigure af WHERE af.id = :id")
    void excluirArtFigurePorId(@Param("id") Long id);

    @Query("SELECT af FROM ArtFigure af WHERE af.anime = :anime ")
    List<ArtFigure> findByAnime(String anime);

    @Query("SELECT af FROM ArtFigure af WHERE af.personagem = :personagem ")
    List<ArtFigure> findByPersonagem(String personagem);

    @Query("SELECT af FROM ArtFigure af WHERE af.tamanho = :tamanho ")

    List<ArtFigure> findByTamanho(String tamanho);

    @Transactional
    @Modifying
    @Query("UPDATE ArtFigure af SET af.anime = :novoAnime WHERE af.id = :id")
    void atualizarAnimePorId(@Param("id") Long id, @Param("novoAnime") String novoAnime);

    @Transactional
    @Modifying
    @Query("UPDATE ArtFigure af SET af.personagem = :novoPersonagem WHERE af.id = :id")
    void atualizarPersonagemPorId(@Param("id") Long id, @Param("novoPersonagem") String novoPersonagem);

    @Transactional
    @Modifying
    @Query("UPDATE ArtFigure af SET af.tamanho = :novoTamanho WHERE af.id = :id")
    void atualizarTamanhoPorId(@Param("id") Long id, @Param("novoTamanho") String novoTamanho);

    List<ArtFigure> findAllByProdutoId(Long id);

    @Query("SELECT a.quantidade FROM ArtFigure a WHERE a.id = :id")
    Integer countQuantidadeById(@Param("id") Long id);

    List<ArtFigure> findAllByQuantidadeIsLessThanEqual(Integer quant);

    @Query("SELECT l FROM ArtFigure l WHERE l.quantidade < 5")
    List<ArtFigure> busqueArtFigureComMenosDeCincoQuant();


}
