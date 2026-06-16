package org.example.cursospring.rapidito.api.mappers;

import org.example.cursospring.rapidito.api.dto.VehiculoDTO;
import org.example.cursospring.rapidito.api.entity.Vehiculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehiculoMapper {
    Vehiculo toEntity(VehiculoDTO vehiculoDTO);

    @Mapping(target = "reservas", ignore = true)
    @Mapping(target = "contratos", ignore = true)
    VehiculoDTO toEntityDTO(Vehiculo vehiculo);

    List<VehiculoDTO> toDTOList(List<Vehiculo> vehiculos);
    List<Vehiculo> toEntityList(List<VehiculoDTO> vehiculos);
}
