package com.project.shopanime.service.produtos;


import com.project.shopanime.model.produtos.Produto;
import com.project.shopanime.repository.produtos.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.buscarTodosProdutos();
    }

    public List<Produto> buscarProdutosDisponiveis() {
        return produtoRepository.buscarProdutosDisponiveis();
    }

    public Optional<Produto> buscarProdutoPorId(Long id) {
        return produtoRepository.buscarProdutoPorId(id);
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            produto.setDisponibilidade(produtoAtualizado.getDisponibilidade());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setDescricao(produtoAtualizado.getDescricao());
            return produtoRepository.save(produto);
        } else {
            throw new RuntimeException("Produto não encontrado com o ID: " + id);
        }
    }

    public void excluirProduto(Long id) {
        produtoRepository.excluirProdutoPorId(id);
    }
}
