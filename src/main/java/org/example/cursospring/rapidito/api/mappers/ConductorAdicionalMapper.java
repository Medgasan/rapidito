package org.example.cursospring.rapidito.api.mappers;

import org.example.cursospring.rapidito.api.dto.ConductorAdicionalDTO;
import org.example.cursospring.rapidito.api.entity.ConductorAdicional;

import java.util.List;

public interface ConductorAdicionalMapper {

    ConductorAdicional toEntity(ConductorAdicionalDTO conductorAdicionalDTO);

    List<ConductorAdicional> toEntityList(List<ConductorAdicionalDTO> conductorAdicionalDTOs);

    ConductorAdicionalDTO toDTO(ConductorAdicional conductorAdicional);

    List<ConductorAdicionalDTO> toDTOList(List<ConductorAdicional> conductorAdicionales);

}
