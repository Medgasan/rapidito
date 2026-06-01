package org.example.cursospring.rapidito.api.service.interfaces;

import org.example.cursospring.rapidito.api.dto.ReservaDTO;
import org.example.cursospring.rapidito.api.entity.Reserva;

import java.time.LocalDate;
import java.util.List;


public interface IReservaService {

    ReservaDTO crearReserva(ReservaDTO reservaDTO);
    ReservaDTO mostrarReserva(Long id);
    ReservaDTO actualizarReserva(ReservaDTO reservaDTO);
    boolean eliminarReserva(ReservaDTO reservaDTO);
    List<ReservaDTO> mostrarReservas();
    ReservaDTO cancelarReserva(Long id);
    List<ReservaDTO> mostrarReservasPorFiltro(Long clienteId, Reserva.EstadoReserva estadoReserva, LocalDate fechaInicio, LocalDate fechaFin);
}
