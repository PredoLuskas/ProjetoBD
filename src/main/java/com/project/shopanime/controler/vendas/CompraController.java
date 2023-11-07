package com.project.shopanime.controler.vendas;

import com.project.shopanime.model.produtos.Produto;
import com.project.shopanime.model.vendas.Cliente;
import com.project.shopanime.model.vendas.Compra;
import com.project.shopanime.model.vendas.CompraRequest;
import com.project.shopanime.service.vendas.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compras")
public class CompraController {

    private final CompraService compraService;

    @Autowired
    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping
    public ResponseEntity<List<Compra>> buscarTodasCompras() {
        List<Compra> compras = compraService.buscarTodasCompras();
        return new ResponseEntity<>(compras, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> buscarCompraPorId(@PathVariable Long id) {
        Optional<Compra> compra = compraService.buscarCompraPorId(id);
        return compra.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Um cliente pode realizar várias compras no sistema. E cada compra possui um ou mais itens.
    @PostMapping
    public ResponseEntity<Compra> salvarCompra(@RequestBody Compra compra) {
        Compra novaCompra = compraService.salvarCompra(compra);
        return new ResponseEntity<>(novaCompra, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCompraPorId(@PathVariable Long id) {
        compraService.excluirCompraPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/porVendedor/{id}")
    public ResponseEntity<List<Compra>> buscarComprasPorVendedorId(@PathVariable Long id) {
        List<Compra> compras = compraService.buscarComprasPorVendedorId(id);
        return new ResponseEntity<>(compras, HttpStatus.OK);
    }

    @GetMapping("/porCliente/{id}")
    public ResponseEntity<List<Compra>> buscarComprasPorClienteId(@PathVariable Long id) {
        List<Compra> compras = compraService.buscarComprasPorClienteId(id);
        return new ResponseEntity<>(compras, HttpStatus.OK);
    }
    @GetMapping("/comprasPeriodo")
    public ResponseEntity<List<Compra>> getComprasPorAnoEMes(
            @RequestParam("ano") int ano,
            @RequestParam("mes") int mes
    ) {
        List<Compra> compras = compraService.getComprasPorAnoEMes(ano, mes);
        return new ResponseEntity<>(compras, HttpStatus.OK);
    }
    @PostMapping("/realizar-compra")
    public ResponseEntity<BigDecimal> realizarCompraVinculadoCliente(
            @RequestBody CompraRequest compraRequest
    ) {

        BigDecimal compra = compraService.realizarCompraVinculadoCliente(compraRequest.getIdCliente(), compraRequest.getIdProdutos(), compraRequest.getFormaPagamento(), compraRequest.getIdItem(),compraRequest.getIdVendedor());

        return new ResponseEntity<>(compra, HttpStatus.CREATED);
    }
}
