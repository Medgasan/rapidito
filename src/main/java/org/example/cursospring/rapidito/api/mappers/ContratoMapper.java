package org.example.cursospring.rapidito.api.mappers;

import org.example.cursospring.rapidito.api.dto.ContratoDTO;
import org.example.cursospring.rapidito.api.entity.Contrato;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface ContratoMapper {

    Contrato toContrato(ContratoDTO contratoDTO);
    List<Contrato> toContratoList(List<ContratoDTO> contratos);

    @Mapping(target = "vehiculo.contratos",  ignore = true)
    @Mapping(target = "vehiculo.reservas",  ignore = true)
    @Mapping(target = "cliente.reservas",  ignore = true)
    @Mapping(target = "cliente.contratos",  ignore = true)
    ContratoDTO toContratoDTO(Contrato contrato);

    @Mapping(target = "vehiculo.contratos",  ignore = true)
    @Mapping(target = "vehiculo.reservas",  ignore = true)
    @Mapping(target = "cliente.reservas",  ignore = true)
    @Mapping(target = "cliente.contratos",  ignore = true)
    List<ContratoDTO> toContratoDTOList(List<Contrato> contratos);

}
