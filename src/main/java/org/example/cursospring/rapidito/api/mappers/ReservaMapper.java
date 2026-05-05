package org.example.cursospring.rapidito.api.mappers;

import org.example.cursospring.rapidito.api.dto.ReservaDTO;
import org.example.cursospring.rapidito.api.entity.Reserva;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservaMapper {
    ReservaDTO toReservaDTO(Reserva reserva);
    List<ReservaDTO> toReservaDTOList(List<Reserva> reservas);
    List<Reserva> toReservasList(List<ReservaDTO> reservaDTOS);
    Reserva toReserva(ReservaDTO reservaDTO);
}
