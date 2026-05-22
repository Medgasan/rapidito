package org.example.cursospring.rapidito.api.service;

import org.example.cursospring.rapidito.api.dto.ContratoDTO;
import org.jspecify.annotations.Nullable;


import java.util.List;

public interface IContratoService {
    ContratoDTO crearContrato(ContratoDTO contratoDTO);
    ContratoDTO mostrarContrato(Long id);
    ContratoDTO actualizarContrato(ContratoDTO contratoDTO);
    boolean eliminarContrato(ContratoDTO contratoDTO);
    List<ContratoDTO> mostrarContratos();
    ContratoDTO crearContratoDesdeReserva(Long id);
    ContratoDTO cerrarContrato(Long id);

    @Nullable ContratoDTO cancelarContrato(Long id);
}
