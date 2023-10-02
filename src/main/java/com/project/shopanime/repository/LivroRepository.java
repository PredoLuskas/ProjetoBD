package com.project.shopanime.repository;

import com.project.shopanime.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    // Buscar todos os livros
    List<Livro> findAll();

    // Buscar um livro pelo ID
    Optional<Livro> findById(Long id);

    // Inserir um novo livro
    <S extends Livro> S save(S livro);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO livro (nome, quantPag, author,idioma,quantidade, produto_id) VALUES (:nome,:quantPag, :author,:idioma,:quantidade,:produtoId)", nativeQuery = true)
    int inserirLivro(@Param("nome") String nome, @Param("quantPag") int quantPag, @Param("author") String author, @Param("idioma") String idioma, @Param("quantidade") Integer quantidade, @Param("produtoId") Long produtoId);
    // Excluir um livro pelo ID
    @Transactional
    @Modifying
    @Query("DELETE FROM Livro l WHERE l.id = :id")
    void excluirLivroPorId(@Param("id") Long id);

    // Buscar livros por nome
    List<Livro> findByNome(String nome);

    // Buscar livros por autor
    List<Livro> findByAuthor(String autor);

    // Buscar livros por idioma
    List<Livro> findByIdioma(String idioma);

    // Atualizar o nome de um livro pelo ID
    @Transactional
    @Modifying
    @Query("UPDATE Livro l SET l.nome = :novoNome WHERE l.id = :id")
    void atualizarNomePorId(@Param("id") Long id, @Param("novoNome") String novoNome);

    // Atualizar o autor de um livro pelo ID
    @Transactional
    @Modifying
    @Query("UPDATE Livro l SET l.author = :novoAutor WHERE l.id = :id")
    void atualizarAutorPorId(@Param("id") Long id, @Param("novoAutor") String novoAutor);

    // Atualizar o idioma de um livro pelo ID
    @Transactional
    @Modifying
    @Query("UPDATE Livro l SET l.idioma = :novoIdioma WHERE l.id = :id")
    void atualizarIdiomaPorId(@Param("id") Long id, @Param("novoIdioma") String novoIdioma);
}
