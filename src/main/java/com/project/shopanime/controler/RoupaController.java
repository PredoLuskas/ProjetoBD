package com.project.shopanime.controler;

import com.project.shopanime.dto.RoupaDTO;
import com.project.shopanime.model.Roupa;
import com.project.shopanime.service.RoupaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roupas")
public class RoupaController {

    private final RoupaService roupaService;

    @Autowired
    public RoupaController(RoupaService roupaService) {
        this.roupaService = roupaService;
    }

    @GetMapping
    public ResponseEntity<List<RoupaDTO>> buscarTodasRoupas() {
        List<Roupa> roupas = roupaService.buscarTodasRoupas();
        List<RoupaDTO> roupasDTO = roupaService.converterParaDTO(roupas);
        return new ResponseEntity<>(roupasDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoupaDTO> buscarRoupaPorId(@PathVariable Long id) {
        Optional<Roupa> roupa = roupaService.buscarRoupaPorId(id);
        return roupa.map(value -> new ResponseEntity<>(roupaService.converterParaDTO(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/inserir-com-produto")
    public ResponseEntity<String> inserirRoupaComProduto(@RequestBody RoupaDTO roupaDTO) {
       roupaService.inserirRoupaComProduto(
                roupaDTO.getProdutoId(),
                roupaDTO.getTipovestimenta(),
                roupaDTO.getTamanho(),
                roupaDTO.getCor(),
                roupaDTO.getQuantidade()
        );
        return new ResponseEntity<>("Roupa adicionada com sucesso", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirRoupaPorId(@PathVariable Long id) {
        roupaService.excluirRoupaPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/buscarPorTipoVestimenta")
    public ResponseEntity<List<RoupaDTO>> buscarRoupasPorTipoVestimenta(@RequestParam String tipoVestimenta) {
        List<Roupa> roupas = roupaService.buscarRoupasPorTipoVestimenta(tipoVestimenta);
        return new ResponseEntity<>(roupaService.converterParaDTO(roupas), HttpStatus.OK);
    }

    @GetMapping("/buscarPorTamanho")
    public ResponseEntity<List<RoupaDTO>> buscarRoupasPorTamanho(@RequestParam String tamanho) {
        List<Roupa> roupas = roupaService.buscarRoupasPorTamanho(tamanho);
        return new ResponseEntity<>(roupaService.converterParaDTO(roupas), HttpStatus.OK);
    }

    @GetMapping("/buscarPorCor")
    public ResponseEntity<List<RoupaDTO>> buscarRoupasPorCor(@RequestParam String cor) {
        List<Roupa> roupas = roupaService.buscarRoupasPorCor(cor);
        return new ResponseEntity<>(roupaService.converterParaDTO(roupas), HttpStatus.OK);
    }

    @PutMapping("/{id}/atualizarTipoVestimenta")
    public ResponseEntity<Void> atualizarTipoVestimentaRoupa(@PathVariable Long id, @RequestParam String novoTipoVestimenta) {
        roupaService.atualizarTipoVestimentaRoupa(id, novoTipoVestimenta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/atualizarTamanho")
    public ResponseEntity<Void> atualizarTamanhoRoupa(@PathVariable Long id, @RequestParam String novoTamanho) {
        roupaService.atualizarTamanhoRoupa(id, novoTamanho);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/atualizarCor")
    public ResponseEntity<Void> atualizarCorRoupa(@PathVariable Long id, @RequestParam String novaCor) {
        roupaService.atualizarCorRoupa(id, novaCor);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
