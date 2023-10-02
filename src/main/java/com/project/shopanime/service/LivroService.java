package com.project.shopanime.service;

import com.project.shopanime.dto.LivroDTO;
import com.project.shopanime.dto.RoupaDTO;
import com.project.shopanime.model.Livro;
import com.project.shopanime.model.Roupa;
import com.project.shopanime.repository.LivroRepository;
import com.project.shopanime.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final ProdutoRepository produtoRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository, ProdutoRepository produtoRepository) {
        this.livroRepository = livroRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<Livro> buscarTodosLivros() {
        return livroRepository.findAll();
    }

    public Optional<Livro> buscarLivroPorId(Long id) {
        return livroRepository.findById(id);
    }

    public boolean inserirLivro(long id, String nome, int quantPag, String author, String idioma, Integer quantidade) {
        produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: " + id));

        livroRepository.inserirLivro(nome, quantPag,author,idioma, quantidade,id);
        return true;
    }
    public void excluirLivroPorId(Long id) {
        livroRepository.excluirLivroPorId(id);
    }

    public List<Livro> buscarLivrosPorNome(String nome) {
        return livroRepository.findByNome(nome);
    }

    public List<Livro> buscarLivrosPorAutor(String autor) {
        return livroRepository.findByAuthor(autor);
    }

    public List<Livro> buscarLivrosPorIdioma(String idioma) {
        return livroRepository.findByIdioma(idioma);
    }

    public void atualizarNomeLivro(Long id, String novoNome) {
        livroRepository.atualizarNomePorId(id, novoNome);
    }

    public void atualizarAutorLivro(Long id, String novoAutor) {
        livroRepository.atualizarAutorPorId(id, novoAutor);
    }

    public void atualizarIdiomaLivro(Long id, String novoIdioma) {
        livroRepository.atualizarIdiomaPorId(id, novoIdioma);
    }

    public List<LivroDTO> converterParaDTO(List<Livro> livros) {
        return livros.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public LivroDTO converterParaDTO(Livro livro) {
        LivroDTO dto = new LivroDTO();
        dto.setId(livro.getId());
        dto.setAuthor(livro.getAuthor());
        dto.setNome(livro.getNome());
        dto.setIdioma(livro.getIdioma());
        dto.setQuantPag(livro.getQuantPag());
        dto.setQuantidade(livro.getQuantidade());
        return dto;
    }
}
