package com.project.shopanime.service;

import com.project.shopanime.dto.ArtFigureDTO;
import com.project.shopanime.dto.RoupaDTO;
import com.project.shopanime.model.ArtFigure;
import com.project.shopanime.model.Produto;
import com.project.shopanime.model.Roupa;
import com.project.shopanime.repository.ArtFigureRepository;
import com.project.shopanime.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtFigureService {

    private final ArtFigureRepository artFigureRepository;

    private final ProdutoRepository produtoRepository;
    @Autowired
    public ArtFigureService(ArtFigureRepository artFigureRepository, ProdutoRepository produtoRepository) {
        this.artFigureRepository = artFigureRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<ArtFigure> buscarTodasArtFigures() {
        return artFigureRepository.findAll();
    }

    public Optional<ArtFigure> buscarArtFigurePorId(Long id) {
        return artFigureRepository.findById(id);
    }

    public ArtFigure salvarArtFigure(ArtFigure artFigure) {
        return artFigureRepository.save(artFigure);
    }
    public boolean inserirArtFigure(long id, String anime, String personagem, String tamanho, Integer quantidade) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: " + id));

        artFigureRepository.inserirArtFigure(anime, personagem,tamanho,quantidade,id);
        return true;
    }
    public void excluirArtFigurePorId(Long id) {
        artFigureRepository.excluirArtFigurePorId(id);
    }

    public List<ArtFigure> buscarArtFiguresPorAnime(String anime) {
        return artFigureRepository.findByAnime(anime);
    }

    public List<ArtFigure> buscarArtFiguresPorPersonagem(String personagem) {
        return artFigureRepository.findByPersonagem(personagem);
    }

    public List<ArtFigure> buscarArtFiguresPorTamanho(String tamanho) {
        return artFigureRepository.findByTamanho(tamanho);
    }

    public void atualizarAnimeArtFigure(Long id, String novoAnime) {
        artFigureRepository.atualizarAnimePorId(id, novoAnime);
    }

    public void atualizarPersonagemArtFigure(Long id, String novoPersonagem) {
        artFigureRepository.atualizarPersonagemPorId(id, novoPersonagem);
    }

    public void atualizarTamanhoArtFigure(Long id, String novoTamanho) {
        artFigureRepository.atualizarTamanhoPorId(id, novoTamanho);
    }

    public List<ArtFigureDTO> converterParaDTO(List<ArtFigure> artFigures) {
        return artFigures.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public ArtFigureDTO converterParaDTO(ArtFigure artFigure) {
        ArtFigureDTO dto = new ArtFigureDTO();
        dto.setAnime(artFigure.getAnime());
        dto.setId(artFigure.getId());
        dto.setPersonagem(artFigure.getPersonagem());
        dto.setTamanho(artFigure.getTamanho());
        dto.setQuantidade(artFigure.getQuantidade());
        return dto;
    }

}
