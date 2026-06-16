package org.example.cursospring.rapidito.api.service.validation;

import org.example.cursospring.rapidito.api.dto.DatosConductorDTO;
import org.example.cursospring.rapidito.api.dto.VehiculoDTO;
import org.example.cursospring.rapidito.api.entity.Vehiculo;
import org.example.cursospring.rapidito.api.exception.ReglaNegocioException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class EdadMinimaConductorRegla implements ReglaValidacionConductor {


    @Value("${config.alquiler.edad-minima-standar}")
    private int edadMinima;
    @Value("${config.alquiler.edad-minima-premium}")
    private int edadMinimaPremium;


    @Override
    public void validar(DatosConductorDTO datosConductor, VehiculoDTO vehiculoDTO) {
        LocalDate hoy = LocalDate.now();
        LocalDate fechaNacimiento = datosConductor.getFechaNacimiento();
        int edadCalculada = Period.between(fechaNacimiento, hoy).getYears();

        boolean isVehiculoPremium = Vehiculo.TipoVehiculo.valueOf(vehiculoDTO.getTipo()) == Vehiculo.TipoVehiculo.PREMIUM;

        if (edadCalculada < edadMinima) {
            throw new ReglaNegocioException("Riesgo rechazado: El conductor debe tener al menos " + edadMinima + " años de edad.");
        }
        if (isVehiculoPremium && edadCalculada < edadMinimaPremium) {
            throw new ReglaNegocioException("Riesgo rechazado: El conductor debe tener al menos " + edadMinimaPremium + " años de edad para alquilar un vehículo premium.");
        }
    }
}

/*
Antigüedad mínima: La ley no exige un mínimo, pero las aseguradoras sí.
Lo estándar en el sector es exigir un mínimo de 1 o 2 años de antigüedad con el carnet.

Edad mínima: Generalmente 21 años (para categorías básicas) o 25 años (para vehículos premium).
Si el conductor tiene entre 21 y 24 años, legalmente se puede alquilar, pero el sistema debe
aplicar un suplemento por "Conductor Joven" debido al coste del seguro.
 */