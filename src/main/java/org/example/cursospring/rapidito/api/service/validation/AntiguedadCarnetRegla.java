package org.example.cursospring.rapidito.api.service.validation;

import org.example.cursospring.rapidito.api.dto.DatosConductorDTO;
import org.example.cursospring.rapidito.api.dto.VehiculoDTO;
import org.example.cursospring.rapidito.api.exception.ReglaNegocioException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class AntiguedadCarnetRegla implements ReglaValidacionConductor {

    @Value("${config.alquiler.anos-minimos-carnet}")
    private int anosMinimosCarnet;

    @Override
    public void validar(DatosConductorDTO datosConductor, VehiculoDTO vehiculoDTO) {
        LocalDate fechaCarnet = datosConductor.getFechaObtencion();

        if (fechaCarnet == null) {
            throw new ReglaNegocioException("La fecha de obtención del carnet es obligatoria.");
        }

        int anosAntiguedad = Period.between(fechaCarnet, LocalDate.now()).getYears();

        if (anosAntiguedad < anosMinimosCarnet) {
            throw new ReglaNegocioException("Riesgo rechazado: El conductor requiere un mínimo de " + anosMinimosCarnet + " años de experiencia.");
        }
    }
}
