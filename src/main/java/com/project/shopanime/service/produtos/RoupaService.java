package com.project.shopanime.service.produtos;

import com.project.shopanime.dto.RoupaDTO;
import com.project.shopanime.model.produtos.Livro;
import com.project.shopanime.model.produtos.Produto;
import com.project.shopanime.model.produtos.Roupa;
import com.project.shopanime.repository.produtos.ProdutoRepository;
import com.project.shopanime.repository.produtos.RoupaRepository;
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
        return roupaRepository.buscarTodos();
    }

    public List<Roupa> buscarRoupasComEstoqueMenorQueCinco() {
        return roupaRepository.busqueRoupasComMenosDeCincoQuant();
    }
    public Optional<Roupa> buscarRoupaPorId(Long id) {
        return Optional.ofNullable(roupaRepository.buscarPorId(id));
    }

    public Roupa inserirRoupaComProduto(Long produtoId, String tipovestimenta, String tamanho, String cor, Integer quantidade) {
        Roupa novaRoupa = new Roupa();
        novaRoupa.setTipoVestimenta(tipovestimenta);
        novaRoupa.setTamanho(tamanho);
        novaRoupa.setCor(cor);
        novaRoupa.setQuantidade(quantidade);

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: " + produtoId));

        novaRoupa.setProduto(produto);

        roupaRepository.inserirRoupa(tipovestimenta, tamanho, cor, quantidade, produtoId);
        return novaRoupa;
    }

    public void excluirRoupaPorId(Long id) {
        roupaRepository.excluirRoupaPorId(id);
    }

    public List<Roupa> buscarRoupasPorTipoVestimenta(String tipoVestimenta) {
        return roupaRepository.buscarPorVestimenta(tipoVestimenta);
    }

    public List<Roupa> buscarRoupasPorTamanho(String tamanho) {
        return roupaRepository.buscarPorTamanho(tamanho);
    }

    public List<Roupa> buscarRoupasPorCor(String cor) {
        return roupaRepository.buscarPorCor(cor);
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
