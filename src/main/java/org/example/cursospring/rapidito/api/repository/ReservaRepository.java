package org.example.cursospring.rapidito.api.repository;

import jakarta.validation.constraints.NotNull;
import org.example.cursospring.rapidito.api.dto.ReservaDTO;
import org.example.cursospring.rapidito.api.entity.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;


@Repository
@Validated
public interface ReservaRepository extends JpaRepository<Reserva,Long> {

    @Query("""
    SELECT r FROM Reserva r
        WHERE r.cliente.id = :clienteId
        AND (r.fechaInicio >= :fechaInicio OR r.fechaFin <= :fechaFin)
        AND (:estadoReserva IS NULL OR r.estado = :estadoReserva)
""")
    List<Reserva> findByFiltro(
            @NotNull(message = "El ID del cliente es obligatorio y no puede ser nulo") Long clienteId,
            Reserva.EstadoReserva estadoReserva,
            LocalDate fechaInicio, LocalDate fechaFin
    );



}
