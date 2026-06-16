package org.example.cursospring.rapidito.api.service.validation;

import org.example.cursospring.rapidito.api.dto.DatosConductorDTO;
import org.example.cursospring.rapidito.api.dto.VehiculoDTO;

public interface ReglaValidacionConductor {
    void validar(DatosConductorDTO datosConductor, VehiculoDTO vehiculoDTO);
}
