package org.example.cursospring.rapidito.api.mappers;

import org.example.cursospring.rapidito.api.dto.ContratoDTO;
import org.example.cursospring.rapidito.api.entity.Contrato;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface ContratoMapper {
    Contrato toContrato(ContratoDTO contratoDTO);
    ContratoDTO toContratoDTO(Contrato contrato);
    List<ContratoDTO> toContratosDTOList(List<Contrato> contratos);
    List<Contrato> toContratosList(List<ContratoDTO> contratos);
}
