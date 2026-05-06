package org.example.cursospring.rapidito.api.mappers;

import org.example.cursospring.rapidito.api.dto.VehiculoDTO;
import org.example.cursospring.rapidito.api.entity.Vehiculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehiculoMapper {
    Vehiculo toVehiculo(VehiculoDTO vehiculoDTO);

    @Mapping(target = "reservas", ignore = true)
    @Mapping(target = "contratos", ignore = true)
    VehiculoDTO toVehiculoDTO(Vehiculo vehiculo);

    @Mapping(target = "reservas", ignore = true)
    @Mapping(target = "contratos", ignore = true)
    List<VehiculoDTO> toVehiculoDTOList(List<Vehiculo> vehiculos);
    List<VehiculoDTO> toVehiculosList(List<Vehiculo> vehiculos);
}
