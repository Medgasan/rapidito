package org.example.cursospring.rapidito.api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;

@Data
public class ClienteDTO {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    private String direccion;
    private String telefono;

    @Email(message = "Email inválido")
    private String email;

    private List<ReservaDTO> reservas;
    private List<ContratoDTO> contratos;
}
