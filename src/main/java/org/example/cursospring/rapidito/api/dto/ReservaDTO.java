package org.example.cursospring.rapidito.api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservaDTO {

    private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private VehiculoDTO vehiculo;
    private ClienteDTO cliente;

}
