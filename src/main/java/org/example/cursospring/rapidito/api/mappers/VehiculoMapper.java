package org.example.cursospring.rapidito.api.mappers;

import org.example.cursospring.rapidito.api.dto.VehiculoDTO;
import org.example.cursospring.rapidito.api.entity.Vehiculo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehiculoMapper {
    Vehiculo toVehiculo(VehiculoDTO vehiculoDTO);
    VehiculoDTO toVehiculoDTO(Vehiculo vehiculo);
    List<VehiculoDTO> toVehiculoDTOList(List<Vehiculo> vehiculos);
    List<VehiculoDTO> toVehiculosList(List<Vehiculo> vehiculos);
}
