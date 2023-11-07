package com.project.shopanime.service.vendas;

import com.project.shopanime.model.vendas.Cliente;
import com.project.shopanime.repository.vendas.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> buscarTodosClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente salvarCliente(Cliente Cliente) {
        return clienteRepository.save(Cliente);
    }


    public void excluirClientePorId(Long id) {
        clienteRepository.deleteById(id);
    }

    public Optional<Cliente> buscarClientesPorEndereco(String endereco) {
        return clienteRepository.findByEndereco(endereco);
    }

    public Optional<Cliente> buscarClientesPorNome(String nome) {
        return clienteRepository.findByNome(nome);
    }


}
