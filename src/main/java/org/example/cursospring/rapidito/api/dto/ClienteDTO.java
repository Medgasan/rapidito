package org.example.cursospring.rapidito.api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;

@Data
public class ClienteDTO {
    private Long id;

    private String slug;
    private String direccion;
    private String telefono;
    @Email(message = "Email inválido")
    private String email;
    private String sexo;
    private String nacionalidad;

    private DatosConductorDTO datosConductor;

    private List<ReservaDTO> reservas;
    private List<ContratoDTO> contratos;
}
