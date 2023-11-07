package com.project.shopanime.repository.produtos;

import com.project.shopanime.model.produtos.ArtFigure;
import com.project.shopanime.model.produtos.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT l FROM Livro l")
    List<Livro> buscarTodos();

    @Query("SELECT l FROM Livro l WHERE l.id = :id")
    List<Livro> buscarPorId(Long id);

    @Query("SELECT l FROM Livro l WHERE l.nome = :nome")
    List<Livro> buscarPorNome(String nome);

    @Query("SELECT l FROM Livro l WHERE l.author = :autor")
    List<Livro> buscarPorAuthor(String autor);

    @Query("SELECT l FROM Livro l WHERE l.id = :idioma")
    List<Livro> buscarPorIdioma(String idioma);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO livro (nome, quantPag, author,idioma,quantidade, produto_id) VALUES (:nome,:quantPag, :author,:idioma,:quantidade,:produtoId)", nativeQuery = true)
    int inserirLivro(@Param("nome") String nome, @Param("quantPag") int quantPag, @Param("author") String author, @Param("idioma") String idioma, @Param("quantidade") Integer quantidade, @Param("produtoId") Long produtoId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Livro l WHERE l.id = :id")
    void excluirLivroPorId(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Livro l SET l.nome = :novoNome WHERE l.id = :id")
    void atualizarNomePorId(@Param("id") Long id, @Param("novoNome") String novoNome);
    @Transactional
    @Modifying
    @Query("UPDATE Livro l SET l.author = :novoAutor WHERE l.id = :id")
    void atualizarAutorPorId(@Param("id") Long id, @Param("novoAutor") String novoAutor);

    @Transactional
    @Modifying
    @Query("UPDATE Livro l SET l.idioma = :novoIdioma WHERE l.id = :id")
    void atualizarIdiomaPorId(@Param("id") Long id, @Param("novoIdioma") String novoIdioma);

    @Query("SELECT a.quantidade FROM Livro a WHERE a.id = :id")
    Integer countQuantidadeById(@Param("id") Long id);

    @Query("SELECT l FROM Livro l WHERE l.quantidade < 5")
    List<Livro> busqueLivrosComMenosDeCincoQuant();

}
