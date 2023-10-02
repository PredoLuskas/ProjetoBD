package com.project.shopanime.service;

import com.project.shopanime.dto.RoupaDTO;
import com.project.shopanime.model.Produto;
import com.project.shopanime.model.Roupa;
import com.project.shopanime.repository.ProdutoRepository;
import com.project.shopanime.repository.RoupaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoupaService {

    private final RoupaRepository roupaRepository;

    private final ProdutoRepository produtoRepository;
    @Autowired
    public RoupaService(RoupaRepository roupaRepository, ProdutoRepository produtoRepository) {
        this.roupaRepository = roupaRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<Roupa> buscarTodasRoupas() {
        return roupaRepository.findAll();
    }

    public Optional<Roupa> buscarRoupaPorId(Long id) {
        return roupaRepository.findById(id);
    }

/*    public Roupa salvarRoupa(Roupa roupa) {
        return roupaRepository.save(roupa);
    }*/
    public Roupa inserirRoupaComProduto(Long produtoId, String tipovestimenta, String tamanho, String cor, Integer quantidade) {
        // Passo 1: Crie uma instância de Roupa com os detalhes necessários
        Roupa novaRoupa = new Roupa();
        novaRoupa.setTipoVestimenta(tipovestimenta);
        novaRoupa.setTamanho(tamanho);
        novaRoupa.setCor(cor);
        novaRoupa.setQuantidade(quantidade);

        // Passo 2: Carregue o Produto do banco de dados com base no ID
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: " + produtoId));

        // Passo 3: Associe o Produto à Roupa
        novaRoupa.setProduto(produto);

        // Passo 4: Salve a Roupa no banco de dados
        roupaRepository.inserirRoupa(tipovestimenta,tamanho,cor,quantidade,produtoId);
        return novaRoupa;
    }

    public void excluirRoupaPorId(Long id) {
        roupaRepository.excluirRoupaPorId(id);
    }

    public List<Roupa> buscarRoupasPorTipoVestimenta(String tipoVestimenta) {
        return roupaRepository.findByTipoVestimenta(tipoVestimenta);
    }

    public List<Roupa> buscarRoupasPorTamanho(String tamanho) {
        return roupaRepository.findByTamanho(tamanho);
    }

    public List<Roupa> buscarRoupasPorCor(String cor) {
        return roupaRepository.findByCor(cor);
    }

    public void atualizarTipoVestimentaRoupa(Long id, String novoTipoVestimenta) {
        roupaRepository.atualizarTipoVestimentaPorId(id, novoTipoVestimenta);
    }

    public void atualizarTamanhoRoupa(Long id, String novoTamanho) {
        roupaRepository.atualizarTamanhoPorId(id, novoTamanho);
    }

    public void atualizarCorRoupa(Long id, String novaCor) {
        roupaRepository.atualizarCorPorId(id, novaCor);
    }
    public List<RoupaDTO> converterParaDTO(List<Roupa> roupas) {
        return roupas.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public RoupaDTO converterParaDTO(Roupa roupa) {
        RoupaDTO dto = new RoupaDTO();
        dto.setProdutoId(roupa.getId());
        dto.setTipovestimenta(roupa.getTipoVestimenta());
        dto.setTamanho(roupa.getTamanho());
        dto.setCor(roupa.getCor());
        dto.setQuantidade(roupa.getQuantidade());
        return dto;
    }
}
