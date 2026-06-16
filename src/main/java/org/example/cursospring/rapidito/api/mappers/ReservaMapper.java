package org.example.cursospring.rapidito.api.mappers;

import org.example.cursospring.rapidito.api.dto.ReservaDTO;
import org.example.cursospring.rapidito.api.entity.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservaMapper {
    @Mapping(target = "vehiculo.reservas", ignore = true)
    @Mapping(target = "vehiculo.contratos", ignore = true)
    @Mapping(target = "cliente.reservas", ignore = true)
    @Mapping(target = "cliente.contratos", ignore = true)
    ReservaDTO toDTO(Reserva reserva);

    List<ReservaDTO> toDTOList(List<Reserva> reservas);

    List<Reserva> toEntityList(List<ReservaDTO> reservaDTOS);

    Reserva toEntity(ReservaDTO reservaDTO);
}
