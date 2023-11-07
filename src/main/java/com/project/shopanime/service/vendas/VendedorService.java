package com.project.shopanime.service.vendas;

import com.project.shopanime.model.vendas.Compra;
import com.project.shopanime.model.vendas.Vendedor;
import com.project.shopanime.repository.vendas.ClienteRepository;
import com.project.shopanime.repository.vendas.CompraRepository;
import com.project.shopanime.repository.vendas.VendedorRepository;
import com.project.shopanime.utils.StatusPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendaRepository;

    @Autowired
    private CompraRepository compraRepository;


    public List<Vendedor> buscarTodasVendedores() {
        return vendaRepository.findAll();
    }

    public Optional<Vendedor> buscarVendedorPorId(Long id) {
        return vendaRepository.findById(id);
    }

    public Vendedor salvarVendedor(Vendedor Vendedor) {
        return vendaRepository.save(Vendedor);
    }


    public void excluirVendedorPorId(Long id) {
        vendaRepository.deleteById(id);
    }

    //Validar compra do Cliente
    public void validarVenda(long id, String nome) {

        try {
            if (vendaRepository.findByNome(nome).equals(nome)) {
                Optional<Compra> compra = compraRepository.findById(id);

                compra.ifPresent(compraFind -> {
                    compraFind.setStatusPagamento(String.valueOf(StatusPagamento.APPROVE)); // Substitua CONFIRMADO pelo status desejado
                    compraRepository.save(compraFind);
                });

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



}

