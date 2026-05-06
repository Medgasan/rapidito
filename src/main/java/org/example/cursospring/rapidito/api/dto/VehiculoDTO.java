package org.example.cursospring.rapidito.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class VehiculoDTO {

    private Long id;
    private String numeroBastidor;
    private String marca;
    private String modelo;
    private String matricula;
    private String tipoCombustible;
    private float precioDia;
    private List<ReservaDTO> reservas;
    private List<ContratoDTO> contratos;

}
