package org.example.cursospring.rapidito.api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.example.cursospring.rapidito.api.entity.Vehiculo;

import java.math.BigDecimal;
import java.util.List;

@Data
public class VehiculoDTO {
    private Long id;

    @NotBlank(message = "El número de bastidor es obligatorio")
    private String numeroBastidor;

    @NotBlank(message = "La marca es obligatoria")
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    private String modelo;

    @NotBlank(message = "La matrícula es obligatoria")
    private String matricula;

    @NotBlank(message = "El tipo de combustible es obligatorio")
    private String tipoCombustible;

    @NotNull @Positive(message = "El precio debe ser positivo")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal precioDia;

    @NotNull
    private Vehiculo.EstadoVehiculo estado;

    private List<ReservaDTO> reservas;
    private List<ContratoDTO> contratos;

 }
