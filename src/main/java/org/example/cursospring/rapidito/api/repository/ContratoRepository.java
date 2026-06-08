package org.example.cursospring.rapidito.api.repository;

import jakarta.validation.constraints.NotNull;
import org.example.cursospring.rapidito.api.entity.Contrato;
import org.example.cursospring.rapidito.api.entity.Reserva;
import org.example.cursospring.rapidito.api.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato,Long> {

    @Query("""
        SELECT c FROM Contrato c
            WHERE c.cliente.id = :clienteId
            AND c.vehiculo.id = :vehiculoId
            AND (c.fechaInicio >= :fechaInicio OR c.fechaFin <= :fechaFin)
            AND (:estadoContrato IS NULL OR c.estado = :estadoContrato)
    """)
    List<Contrato> findByFiltro(
            @NotNull(message = "El ID del cliente es obligatorio y no puede ser nulo") Long clienteId,
            Long vehiculoId ,
            Contrato.EstadoContrato estadoContrato,
            LocalDate fechaInicio, LocalDate fechaFin
    );


    long countByCliente_IdAndEstado(Long clienteId, Contrato.EstadoContrato estado);


    @Query("""
    SELECT COALESCE(SUM(c.totalContrato), 0)
    FROM Contrato c
    WHERE c.cliente.id = :clienteId
      AND c.estado = :estado
    """)
    BigDecimal sumImporteByCliente_IdAndEstado(long clienteId, Contrato.EstadoContrato estado);

}
