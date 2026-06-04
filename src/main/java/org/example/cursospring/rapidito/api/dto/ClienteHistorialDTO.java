package org.example.cursospring.rapidito.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteHistorialDTO {
    private ClienteDTO cliente;
    private List<ReservaDTO> reservas;
    private List<ContratoDTO> contratos;
    private long totalAlquileresCerrados;
    private BigDecimal importeAcumulado;
}
