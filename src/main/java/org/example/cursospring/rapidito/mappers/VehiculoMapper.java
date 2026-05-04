package org.example.cursospring.rapidito.mappers;

import org.example.cursospring.rapidito.dto.VehiculoDTO;
import org.example.cursospring.rapidito.entity.Vehiculo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface VehiculoMapper {
    Vehiculo toVehiculo(Vehiculo vehiculo);
    VehiculoDTO toVehiculoDTO(Vehiculo vehiculo);
    List<VehiculoDTO> toVehiculoDTOList(List<Vehiculo> vehiculos);
    List<Vehiculo> toVehiculosList(List<Vehiculo> vehiculos);
}
