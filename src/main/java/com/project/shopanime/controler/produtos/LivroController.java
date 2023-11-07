package com.project.shopanime.controler.produtos;

import com.project.shopanime.dto.LivroDTO;
import com.project.shopanime.model.produtos.Livro;
import com.project.shopanime.service.produtos.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> buscarTodosLivros() {
        List<Livro> livros = livroService.buscarTodosLivros();
        return new ResponseEntity<>(livroService.converterParaDTO(livros), HttpStatus.OK);
    }

    @GetMapping("/estoque-cinco")
    public ResponseEntity<List<Livro>> buscarLivrosComEstoqueMenorQueCinco() {
        List<Livro> livros = livroService.buscarLivrosComEstoqueMenorQueCinco();
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> buscarLivroPorId(@PathVariable Long id) {
        List<Livro> livro = livroService.buscarLivroPorId(id);
        return livro.stream().map(value -> new ResponseEntity<>(livroService.converterParaDTO(value), HttpStatus.OK))
                .findAny().orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/inserir-com-produto")
    public ResponseEntity<String> inserirLivroComProduto(@RequestBody LivroDTO livroDTO) {
        Boolean livro = livroService.inserirLivro(
                livroDTO.getId(),
                livroDTO.getNome(),
                livroDTO.getQuantPag(),
                livroDTO.getAuthor(),
                livroDTO.getIdioma(),
                livroDTO.getQuantidade()
        );
        if (livro.equals(true))
            return new ResponseEntity<>("Livro adicionada com sucesso", HttpStatus.CREATED);
        else {
            return new ResponseEntity<>("Livro não adicionada", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirLivroPorId(@PathVariable Long id) {
        livroService.excluirLivroPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/buscarPorNome")
    public ResponseEntity<List<LivroDTO>> buscarLivrosPorNome(@RequestParam String nome) {
        List<Livro> livros = livroService.buscarLivrosPorNome(nome);
        return new ResponseEntity<>(livroService.converterParaDTO(livros), HttpStatus.OK);
    }

    @GetMapping("/buscarPorAutor")
    public ResponseEntity<List<LivroDTO>> buscarLivrosPorAutor(@RequestParam String autor) {
        List<Livro> livros = livroService.buscarLivrosPorAutor(autor);
        return new ResponseEntity<>(livroService.converterParaDTO(livros), HttpStatus.OK);
    }

    @GetMapping("/buscarPorIdioma")
    public ResponseEntity<List<LivroDTO>> buscarLivrosPorIdioma(@RequestParam String idioma) {
        List<Livro> livros = livroService.buscarLivrosPorIdioma(idioma);
        return new ResponseEntity<>(livroService.converterParaDTO(livros), HttpStatus.OK);
    }

    @PutMapping("/{id}/atualizarNome")
    public ResponseEntity<Void> atualizarNomeLivro(@PathVariable Long id, @RequestParam String novoNome) {
        livroService.atualizarNomeLivro(id, novoNome);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/atualizarAutor")
    public ResponseEntity<Void> atualizarAutorLivro(@PathVariable Long id, @RequestParam String novoAutor) {
        livroService.atualizarAutorLivro(id, novoAutor);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/atualizarIdioma")
    public ResponseEntity<Void> atualizarIdiomaLivro(@PathVariable Long id, @RequestParam String novoIdioma) {
        livroService.atualizarIdiomaLivro(id, novoIdioma);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
