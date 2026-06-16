package org.example.cursospring.rapidito.api.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.cursospring.rapidito.api.entity.Contrato;
import org.example.cursospring.rapidito.api.entity.DatosConductor;
import org.example.cursospring.rapidito.api.entity.Reserva;

@Data
public class ConductorAdicionalDTO {

    private Long id;
    private DatosConductorDTO datosConductor;
    private Reserva reserva;
    private Contrato contrato;


}
