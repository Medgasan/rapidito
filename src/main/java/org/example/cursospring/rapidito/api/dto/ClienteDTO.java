package org.example.cursospring.rapidito.api.dto;

import lombok.Data;
import org.mapstruct.Mapping;

import java.util.List;

@Data
public class ClienteDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String email;

    private List<ReservaDTO> reservas;
    private List<ContratoDTO> contratos;


}
