package org.example.cursospring.rapidito.api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.example.cursospring.rapidito.api.exception.FechasValidas;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@FechasValidas
public class ContratoDTO {

    private Long id;

    private String slug;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFin;

    @Positive
    private int numeroDias;

    @Digits(integer = 8, fraction = 2)
    private BigDecimal precioVehiculo;

    @Digits(integer = 10, fraction = 2)
    private BigDecimal totalContrato;

    @Digits(integer = 10, fraction = 2)
    private BigDecimal fianzaRetenida;

    private DatosConductorDTO conductorHabitual;

    private List<ConductorAdicionalDTO> conductoresAdicionales;

    private String tipoSeguro;

    private VehiculoDTO vehiculo;

    private ClienteDTO cliente;

    private String estado;


}
