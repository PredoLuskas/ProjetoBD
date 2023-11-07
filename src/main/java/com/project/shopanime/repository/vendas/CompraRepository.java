package com.project.shopanime.repository.vendas;

import com.project.shopanime.model.vendas.Cliente;
import com.project.shopanime.model.vendas.Compra;
import com.project.shopanime.model.vendas.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CompraRepository extends JpaRepository<Compra, Long> {


    @Query("SELECT c FROM Compra c WHERE c.vendedor.id = :idVendedor")
   List<Compra> findAllByVendedorId(Long idVendedor);

    @Query("SELECT c FROM Compra c WHERE c.cliente.id = :cliente")
    List<Compra> findAllByClienteId(Long cliente);

    @Query("SELECT c FROM Compra c WHERE EXTRACT(YEAR FROM c.dataCompra) = :ano AND EXTRACT(MONTH FROM c.dataCompra) = :mes")
    List<Compra> findAllComprasByAnoMes(@Param("ano") int ano, @Param("mes") int mes);
}
