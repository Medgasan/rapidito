package org.example.cursospring.rapidito.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClienteDTO {

    private Long id;

    String nombre;
    String apellido;
    String direccion;
    String telefono;
    String email;

//    private List<ReservaDTO> reservas;
//    private List<ContratoDTO> contratos;


}
