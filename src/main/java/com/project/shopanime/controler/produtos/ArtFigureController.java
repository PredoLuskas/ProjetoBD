package com.project.shopanime.controler.produtos;

import com.project.shopanime.dto.ArtFigureDTO;
import com.project.shopanime.model.produtos.ArtFigure;
import com.project.shopanime.model.produtos.Roupa;
import com.project.shopanime.service.produtos.ArtFigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/art-figures")
public class ArtFigureController {

    private final ArtFigureService artFigureService;

    @Autowired
    public ArtFigureController(ArtFigureService artFigureService) {
        this.artFigureService = artFigureService;
    }

    @GetMapping
    public ResponseEntity<List<ArtFigureDTO>> buscarTodasArtFigures() {
        List<ArtFigure> artFigures = artFigureService.buscarTodasArtFigures();
        return new ResponseEntity<>(artFigureService.converterParaDTO(artFigures), HttpStatus.OK);
    }
    @GetMapping("/estoque-cinco")
    public ResponseEntity<List<ArtFigure>> buscarLivrosComEstoqueMenorQueCinco() {
        List<ArtFigure> artFigures = artFigureService.buscarRoupasComEstoqueMenorQueCinco();
        return ResponseEntity.ok(artFigures);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtFigure> buscarArtFigurePorId(@PathVariable Long id) {
        Optional<ArtFigure> artFigure = artFigureService.buscarArtFigurePorId(id);
        return artFigure.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ArtFigure> salvarArtFigure(@RequestBody ArtFigure artFigure) {
        ArtFigure novaArtFigure = artFigureService.salvarArtFigure(artFigure);
        return new ResponseEntity<>(novaArtFigure, HttpStatus.CREATED);
    }

    @PostMapping("/inserir-com-produto")
    public ResponseEntity<String> inserirArtFigure(@RequestBody ArtFigureDTO artFigureDTO) {
        Boolean artFigure = artFigureService.inserirArtFigure(
                artFigureDTO.getId(),
                artFigureDTO.getAnime(),
                artFigureDTO.getPersonagem(),
                artFigureDTO.getTamanho(),
                artFigureDTO.getQuantidade()
        );
        if (artFigure.equals(true))
            return new ResponseEntity<>("ArtFigure adicionada com sucesso", HttpStatus.CREATED);
        else {
            return new ResponseEntity<>("ArtFigure não adicionada", HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirArtFigurePorId(@PathVariable Long id) {
        artFigureService.excluirArtFigurePorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/buscarPorAnime")
    public ResponseEntity<List<ArtFigureDTO>> buscarArtFiguresPorAnime(@RequestParam String anime) {
        List<ArtFigure> artFigures = artFigureService.buscarArtFiguresPorAnime(anime);
        return new ResponseEntity<>(artFigureService.converterParaDTO(artFigures), HttpStatus.OK);
    }

    @GetMapping("/buscarPorPersonagem")
    public ResponseEntity<List<ArtFigureDTO>> buscarArtFiguresPorPersonagem(@RequestParam String personagem) {
        List<ArtFigure> artFigures = artFigureService.buscarArtFiguresPorPersonagem(personagem);
        return new ResponseEntity<>(artFigureService.converterParaDTO(artFigures), HttpStatus.OK);
    }

    @GetMapping("/buscarPorTamanho")
    public ResponseEntity<List<ArtFigureDTO>> buscarArtFiguresPorTamanho(@RequestParam String tamanho) {
        List<ArtFigure> artFigures = artFigureService.buscarArtFiguresPorTamanho(tamanho);
        return new ResponseEntity<>(artFigureService.converterParaDTO(artFigures), HttpStatus.OK);
    }

    @PutMapping("/{id}/atualizarAnime")
    public ResponseEntity<Void> atualizarAnimeArtFigure(@PathVariable Long id, @RequestParam String novoAnime) {
        artFigureService.atualizarAnimeArtFigure(id, novoAnime);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/atualizarPersonagem")
    public ResponseEntity<Void> atualizarPersonagemArtFigure(@PathVariable Long id, @RequestParam String novoPersonagem) {
        artFigureService.atualizarPersonagemArtFigure(id, novoPersonagem);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/atualizarTamanho")
    public ResponseEntity<Void> atualizarTamanhoArtFigure(@PathVariable Long id, @RequestParam String novoTamanho) {
        artFigureService.atualizarTamanhoArtFigure(id, novoTamanho);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
