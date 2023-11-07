package com.project.shopanime.controler.vendas;

import com.project.shopanime.model.vendas.Cliente;
import com.project.shopanime.service.vendas.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarTodosClientes() {
        List<Cliente> clientes = clientService.buscarTodosClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

   // Um cliente deve poder verificar o seus dados cadastrais
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clientService.buscarClientePorId(id);
        return cliente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clientService.salvarCliente(cliente);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirClientePorId(@PathVariable Long id) {
        clientService.excluirClientePorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/endereco")
    public ResponseEntity<Optional<Cliente>> buscarClientesPorEndereco(@RequestParam String endereco) {
        Optional<Cliente> clientes = clientService.buscarClientesPorEndereco(endereco);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/nome")
    public ResponseEntity<Cliente> buscarClientesPorNome(@RequestParam String nome) {
        Optional<Cliente> cliente = clientService.buscarClientesPorNome(nome);
        return cliente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
