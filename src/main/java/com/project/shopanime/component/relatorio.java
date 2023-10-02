package com.project.shopanime.component;

import com.project.shopanime.model.Produto;
import com.project.shopanime.service.ProdutoService;
import com.project.shopanime.service.RoupaService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class relatorio {

    //Calcular quantidade de roupas, livros e ArtFigures em estoque
    //Calcular

    ProdutoService produtoService;
    RoupaService roupaService;
/*    public Map<Long, Integer> calcularQuantidadeTotalPorIdProduto() {
        // Crie um mapa para armazenar as somas por ID do produto
        Map<Long, Integer> quantidadePorIdProduto = new HashMap<>();
        List<Produto> produtos = produtoService.buscarProdutosDisponiveis();

        // Itere pela lista de produtos
        for (Produto produto : produtos) {
            Long idProduto = produto.getId();
            Integer quantidade = produto.getQuantidade();
            // Verifique se já existe uma entrada para o ID do produto no mapa
            if (quantidadePorIdProduto.containsKey(idProduto)) {
                // Se sim, adicione a quantidade atual à quantidade existente
                quantidade += quantidadePorIdProduto.get(idProduto);
            }
            // Atualize o mapa com a nova quantidade
            quantidadePorIdProduto.put(idProduto, quantidade);
        }

        return quantidadePorIdProduto;
    }*/
}

