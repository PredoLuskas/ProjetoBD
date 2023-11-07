package com.project.shopanime.repository;

import com.project.shopanime.model.RelatorioVendasMensal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RelatorioVendasMensalRepository extends JpaRepository<RelatorioVendasMensal, Long> {
/*    @Query(value = "SELECT * FROM relatorio_vendas_mensal WHERE mes = :mes", nativeQuery = true)
    List<RelatorioVendasMensal> buscarRelatorioPorMes(@Param("mes") int mes);*/

    Optional<RelatorioVendasMensal> findByAnoAndMes(Integer ano, Integer mes);


}
