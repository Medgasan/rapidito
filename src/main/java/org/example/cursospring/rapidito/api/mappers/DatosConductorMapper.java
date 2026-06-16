package org.example.cursospring.rapidito.api.mappers;

import org.example.cursospring.rapidito.api.dto.DatosConductorDTO;
import org.example.cursospring.rapidito.api.entity.DatosConductor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DatosConductorMapper {

    DatosConductor toEntity(DatosConductorDTO datosConductorDTO);

    List<DatosConductor> toEntityList(List<DatosConductorDTO> datosConductorDTOs);

    DatosConductorDTO toDTO(DatosConductor datosConductor);

    List<DatosConductorDTO> toDTOList(List<DatosConductor> datosConductores);


}
