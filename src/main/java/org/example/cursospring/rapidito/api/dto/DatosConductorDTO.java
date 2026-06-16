package org.example.cursospring.rapidito.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Data
public class DatosConductorDTO {

    @NotBlank(message = "El nombre del conductor es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido del conductor es obligatorio")
    private String apellido;

    @NotBlank(message = "El tipo de documento es obligatorio")
    private String tipoDocumento;

    @NotBlank(message = "El número de documento es obligatorio")
    private String numeroDocumento;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // <-- Manteniendo tu estilo de fechas
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El tipo de carnet es obligatorio")
    private String tipoCarnet;

    @NotBlank(message = "El número de carnet es obligatorio")
    private String numeroCarnet;

    @NotBlank(message = "La validez del carnet es obligatoria")
    private String validezCarnet;

    @NotBlank
    private LocalDate fechaObtencion;

    @NotBlank(message = "El número de soporte del carnet es obligatorio para la DGT")
    private String numeroSoporteCarnet;
}