package org.example.cursospring.rapidito.api.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

@Data
public class ClienteHistorialDTO {
    private ClienteDTO cliente;
    private Page<ReservaDTO> reservas;
    private Page<ContratoDTO> contratos;
    private long totalAlquileresCerrados;
    private BigDecimal importeAcumulado;
}
