package com.project.shopanime.controler;

import com.project.shopanime.dto.RelatorioDTO;
import com.project.shopanime.model.RelatorioVendasMensal;
import com.project.shopanime.service.RelatorioVendasMensalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/relatorio")
public class RelatorioVendasMensalController {
    @Autowired
    private RelatorioVendasMensalService relatorioService;

    @GetMapping("/mensal")
    public ResponseEntity<Optional<RelatorioVendasMensal>> obterRelatorioMensal(
            @RequestParam Integer ano,
            @RequestParam Integer mes
    ) {
        Optional<RelatorioVendasMensal> relatorio = relatorioService.obterRelatorioMensal(ano, mes);
        return new ResponseEntity<>(relatorio, HttpStatus.OK);
    }
}
