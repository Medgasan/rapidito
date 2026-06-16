package org.example.cursospring.rapidito.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.cursospring.rapidito.api.entity.Reserva;
import org.example.cursospring.rapidito.api.exception.FechasValidas;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@FechasValidas // <-- Se mantiene intacta tu validación de negocio cruzada
public class ReservaDTO {

    private Long id;
    private String slug; // Añadido para que viaje en el DTO de respuesta

    @NotNull(message = "La fecha de inicio es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFin;

    private Reserva.EstadoReserva estado; // Mantenemos tu uso directo del Enum

    // Tus relaciones en formato DTO se quedan tal cual
    private VehiculoDTO vehiculo;
    private ClienteDTO cliente;

    /**
     * Nuevos campos requeridos para el cumplimiento legal en España.
     * Con @Valid obligamos a Spring a validar las restricciones internas del conductor.
     */
    @Valid
    @NotNull(message = "Los datos del conductor habitual son obligatorios")
    private DatosConductorDTO conductorHabitual;

    private List<@Valid ConductorAdicionalDTO> conductoresAdicionales = new ArrayList<>();
}