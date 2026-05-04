package org.example.cursospring.rapidito.mappers;

import org.example.cursospring.rapidito.entity.Contrato;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ContratoDTO {
    Contrato toContrato(ContratoDTO contratoDTO);
    ContratoDTO toContratoDTO(Contrato contrato);
    List<ContratoDTO> toContratosDTOList(List<Contrato> contratos);
    List<Contrato> toContratosList(List<ContratoDTO> contratos);
}
