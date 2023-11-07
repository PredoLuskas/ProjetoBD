package com.project.shopanime.service.vendas;

import com.project.shopanime.model.produtos.ArtFigure;
import com.project.shopanime.model.produtos.Livro;
import com.project.shopanime.model.produtos.Produto;
import com.project.shopanime.model.produtos.Roupa;
import com.project.shopanime.model.vendas.Cliente;
import com.project.shopanime.model.vendas.Compra;
import com.project.shopanime.repository.produtos.ArtFigureRepository;
import com.project.shopanime.repository.produtos.LivroRepository;
import com.project.shopanime.repository.produtos.ProdutoRepository;
import com.project.shopanime.repository.produtos.RoupaRepository;
import com.project.shopanime.repository.vendas.ClienteRepository;
import com.project.shopanime.repository.vendas.CompraRepository;
import com.project.shopanime.repository.vendas.VendedorRepository;
import com.project.shopanime.utils.StatusPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ArtFigureRepository artFigureRepository;
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private RoupaRepository roupaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private VendedorRepository vendedorRepository;

    private static BigDecimal descontoCompra(Optional<Cliente> cliente, BigDecimal valorTotal) {
        BigDecimal desconto;
        if (cliente.get().isFaFlamengo() || cliente.get().isFaOnePiece() || cliente.get().getEndereco().equals("Souza")) {
            desconto = valorTotal.multiply(new BigDecimal("0.15"));
            valorTotal = valorTotal.subtract(desconto);
        }
        return valorTotal;
    }

    public List<Compra> buscarTodasCompras() {
        return compraRepository.findAll();
    }

    public Optional<Compra> buscarCompraPorId(Long id) {
        return compraRepository.findById(id);
    }

    public Compra salvarCompra(Compra Compra) {
        return compraRepository.save(Compra);
    }

    public void excluirCompraPorId(Long id) {
        compraRepository.deleteById(id);
    }

    public List<Compra> buscarComprasPorVendedorId(Long id) {
        return compraRepository.findAllByVendedorId(id);
    }

    public List<Compra> buscarComprasPorClienteId(Long id) {
        return compraRepository.findAllByClienteId(id);
    }
    public List<Compra> getComprasPorAnoEMes(int ano, int mes) {
        return compraRepository.findAllComprasByAnoMes(ano, mes);
    }
    public BigDecimal realizarCompraVinculadoCliente(Long idCliente, Long idProduto, String formaPagamento, List<Long> idItem, Long idVendedor) {
        Compra compra = new Compra();

        Optional<Cliente> cliente = clienteRepository.findById(idCliente);


        compra.setDataCompra(LocalDate.now());
        compra.setFormaPagamento(formaPagamento);
        compra.setCliente(cliente.get());
        compra.setStatusPagamento(String.valueOf(StatusPagamento.HOLD));
        compra.setVendedor(vendedorRepository.findById(idVendedor).get());
        compra.setProdutos(produtoRepository.findById(idProduto).get());

        BigDecimal valorTotal = BigDecimal.ZERO;
        BigDecimal desconto = BigDecimal.ZERO;
        valorTotal = compra.getProdutos().getPreco().multiply(BigDecimal.valueOf(idItem.size()));

        if (!compra.getProdutos().getDisponibilidade()) return null;

        validarQuantidade(compra.getProdutos(), idItem);
        valorTotal = descontoCompra(cliente, valorTotal);

        compra.setValorTotal(valorTotal);

        compraRepository.save(compra);

        return valorTotal;
    }

    private void validarQuantidade(Produto produto, List<Long> idItem) {
        Long id = Objects.requireNonNull(produto.getId());

        switch (id.intValue()) {
            case 2:
                List<Roupa> roupaOptional = new ArrayList<>();
                for (Long idProdutosLista : idItem) {
                    Optional<Roupa> roupaOpt = roupaRepository.findById(idProdutosLista);
                    if (roupaOpt.isPresent()) {
                        Roupa roupa = roupaOpt.get();
                        Integer quant = roupa.getQuantidade() - 1;

                        if (quant >= 0) {
                            roupa.setQuantidade(quant);
                            roupaRepository.save(roupa);
                        } else {
                            roupa.setQuantidade(0);
                            produto.setDisponibilidade(false);
                            roupaRepository.save(roupa);
                        }
                        roupaOptional.add(roupa);
                    }
                }

                if (roupaOptional.isEmpty()) {
                    produto.setDisponibilidade(false);
                }

                break;
            case 4:
                List<ArtFigure> artFiguresOptional = new ArrayList<>();
                for (Long idProdutosLista : idItem) {
                    Optional<ArtFigure> artFigureOpt = artFigureRepository.findById(idProdutosLista);
                    if (artFigureOpt.isPresent()) {
                        ArtFigure artFigure = artFigureOpt.get();
                        Integer quant = artFigure.getQuantidade() - 1;

                        if (quant >= 0) {
                            artFigure.setQuantidade(quant);
                            artFigureRepository.save(artFigure);
                        } else {
                            artFigure.setQuantidade(0);
                            produto.setDisponibilidade(false);
                            artFigureRepository.save(artFigure);
                        }
                        artFiguresOptional.add(artFigure);
                    }
                }

                if (artFiguresOptional.isEmpty()) {
                    produto.setDisponibilidade(false);
                }

                break;
            case 3:
                List<Livro> livroOptional = new ArrayList<>();
                for (Long idProdutosLista : idItem) {
                    Optional<Livro> livroOpt = livroRepository.findById(idProdutosLista);
                    if (livroOpt.isPresent()) {
                        Livro livro = livroOpt.get();
                        Integer quant = livro.getQuantidade() - 1;

                        if (quant >= 0) {
                            livro.setQuantidade(quant);
                            livroRepository.save(livro);
                        } else {
                            livro.setQuantidade(0);
                            produto.setDisponibilidade(false);
                            livroRepository.save(livro);
                        }
                        livroOptional.add(livro);
                    }
                }

                if (livroOptional.isEmpty()) {
                    produto.setDisponibilidade(false);
                }

                break;
            default:
        }
    }
}


