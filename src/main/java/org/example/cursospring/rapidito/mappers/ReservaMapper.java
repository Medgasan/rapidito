package org.example.cursospring.rapidito.mappers;

import org.example.cursospring.rapidito.dto.ReservaDTO;
import org.example.cursospring.rapidito.entity.Reserva;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ReservaMapper {
    ReservaDTO toReservaDTOList(Reserva reserva);
    List<ReservaDTO> toReservaDTOList(List<Reserva> reservas);
    List<Reserva> toReservasList(List<Reserva> reservas);
    Reserva toReserva(Reserva reserva);
}
