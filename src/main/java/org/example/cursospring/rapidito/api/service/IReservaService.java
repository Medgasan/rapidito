package org.example.cursospring.rapidito.api.service;

import org.example.cursospring.rapidito.api.dto.ReservaDTO;

import java.util.List;


public interface IReservaService {

    ReservaDTO crearReserva(ReservaDTO reservaDTO);

    ReservaDTO mostrarReserva(Long id);

    ReservaDTO actualizarReserva(ReservaDTO reservaDTO);

    boolean eliminarReserva(ReservaDTO reservaDTO);

    List<ReservaDTO> mostrarReservas();
}
