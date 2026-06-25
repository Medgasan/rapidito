package org.example.cursospring.rapidito.api.dto;

import lombok.Data;
import org.example.cursospring.rapidito.api.entity.Contrato;
import org.example.cursospring.rapidito.api.entity.Reserva;

@Data
public class ConductorAdicionalDTO {

    private Long id;
    private DatosConductorDTO datosConductor;
    private Reserva reserva;
    private Contrato contrato;


}
