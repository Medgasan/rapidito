package org.example.cursospring.rapidito.api.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ContratoDTO {


    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFin;
    private int numeroDias;
    private float precioVehiculo;
    private float totalContrato;
    private VehiculoDTO vehiculo;
    private ClienteDTO cliente;

}
