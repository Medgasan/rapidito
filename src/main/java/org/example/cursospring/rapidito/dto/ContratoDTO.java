package org.example.cursospring.rapidito.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ContratoDTO {


    private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int numeroDias;
    private float precioVehiculo;
    private float totalContrato;
    private VehiculoDTO vehiculo;
    private ClienteDTO cliente;

}
