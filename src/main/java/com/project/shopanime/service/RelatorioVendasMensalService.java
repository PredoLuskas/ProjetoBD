package com.project.shopanime.service;

import com.project.shopanime.dto.LivroDTO;
import com.project.shopanime.dto.RelatorioDTO;
import com.project.shopanime.model.RelatorioVendasMensal;
import com.project.shopanime.model.produtos.Livro;
import com.project.shopanime.repository.RelatorioVendasMensalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RelatorioVendasMensalService {

    @Autowired
    private RelatorioVendasMensalRepository relatorioRepository;

    public Optional<RelatorioVendasMensal> obterRelatorioMensal(Integer ano, Integer mes) {

        Optional<RelatorioVendasMensal> relatorioVendas = relatorioRepository.findByAnoAndMes(ano, mes);
   return  relatorioVendas;
    }

}
