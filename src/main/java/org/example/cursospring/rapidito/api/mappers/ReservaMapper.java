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
    ReservaDTO toReservaDTO(Reserva reserva);

    List<ReservaDTO> toReservaDTOList(List<Reserva> reservas);

    List<Reserva> toReservasList(List<ReservaDTO> reservaDTOS);

//    @Mapping(target = "vehiculo.reservas", ignore = true)
//    @Mapping(target = "vehiculo.contratos", ignore = true)
//    @Mapping(target = "cliente.reservas", ignore = true)
//    @Mapping(target = "cliente.contratos", ignore = true)
    Reserva toReserva(ReservaDTO reservaDTO);
}
