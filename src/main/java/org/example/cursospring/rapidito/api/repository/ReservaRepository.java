package org.example.cursospring.rapidito.api.repository;

import org.example.cursospring.rapidito.api.dto.ReservaDTO;
import org.example.cursospring.rapidito.api.entity.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Long> {

    @Query("""
    SELECT r FROM Reserva r
        WHERE r.cliente.id = :clienteId
        AND (r.fechaInicio >= :fechaFin OR r.fechaFin <= :fechaInicio)
        AND (:estadoReserva IS NULL OR r.estado = :estadoReserva)
""")
    List<Reserva> findByFiltro(Long clienteId, Reserva.EstadoReserva estadoReserva, LocalDate fechaInicio, LocalDate fechaFin);

}
