package org.example.cursospring.rapidito.api.service.interfaces;

import org.example.cursospring.rapidito.api.dto.ContratoDTO;
import org.example.cursospring.rapidito.api.entity.Contrato;


import java.time.LocalDate;
import java.util.List;

public interface IContratoService {
    ContratoDTO crearContrato(ContratoDTO contratoDTO);
    ContratoDTO mostrarContrato(Long id);
    ContratoDTO actualizarContrato(ContratoDTO contratoDTO);
    void eliminarContrato(ContratoDTO contratoDTO);
    List<ContratoDTO> mostrarContratos();
    ContratoDTO crearContratoDesdeReserva(Long id);
    ContratoDTO cerrarContrato(Long id);
    ContratoDTO cancelarContrato(Long id);
    List<ContratoDTO> mostrarContratosPorFiltro(Long clienteId, Long vehiculoId, Contrato.EstadoContrato estadoContrato, LocalDate fechaInicio, LocalDate fechaFin);
}
