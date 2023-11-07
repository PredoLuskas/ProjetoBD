package com.project.shopanime.controler.vendas;

import com.project.shopanime.model.vendas.Vendedor;
import com.project.shopanime.service.vendas.CompraService;
import com.project.shopanime.service.vendas.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;
   
    @Autowired
    private CompraService compraService;

    @GetMapping
    public ResponseEntity<List<Vendedor>> buscarTodasVendedores() {
        List<Vendedor> vendedores = vendedorService.buscarTodasVendedores();
        return ResponseEntity.ok(vendedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> buscarVendedorPorId(@PathVariable Long id) {
        Optional<Vendedor> vendedor = vendedorService.buscarVendedorPorId(id);
        return vendedor.map(value -> ResponseEntity.ok(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Vendedor> salvarVendedor(@RequestBody Vendedor vendedor) {
        Vendedor novoVendedor = vendedorService.salvarVendedor(vendedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVendedor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVendedorPorId(@PathVariable Long id) {
        vendedorService.excluirVendedorPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/validar-venda{nome}")
    public ResponseEntity<String> validarVenda(@PathVariable Long id, @PathVariable String nome) {
        try {
            vendedorService.validarVenda(id, nome);
            return ResponseEntity.ok("Venda validada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao validar a venda: " + e.getMessage());
        }
    }
}
