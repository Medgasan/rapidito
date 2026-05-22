package org.example.cursospring.rapidito.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.cursospring.rapidito.api.entity.Reserva;
import org.example.cursospring.rapidito.api.exception.FechasValidas;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Data
@FechasValidas
public class ReservaDTO {
    private Long id;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFin;

    @NotNull
    private Reserva.EstadoReserva estado;


    private VehiculoDTO vehiculo;
    private ClienteDTO cliente;
}
