package org.example.cursospring.rapidito.api.service;

import org.example.cursospring.rapidito.api.dto.ReservaDTO;
import org.example.cursospring.rapidito.api.entity.Reserva;
import org.example.cursospring.rapidito.api.mappers.ReservaMapper;
import org.example.cursospring.rapidito.api.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService implements IReservaService {


    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;


    public ReservaService(ReservaRepository reservaRepository, ReservaMapper reservaMapper) {
        this.reservaRepository = reservaRepository;
        this.reservaMapper = reservaMapper;
    }



    @Override
    public List<ReservaDTO> mostrarReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return reservaMapper.toReservaDTOList(reservas);
    }


    @Override
    public ReservaDTO crearReserva(ReservaDTO reservaDTO) {
        Reserva reserva = reservaMapper.toReserva(reservaDTO);
        reserva  = reservaRepository.save(reserva);
        return reservaMapper.toReservaDTO(reserva);
    }

    @Override
    public ReservaDTO mostrarReserva(Long id) {
        Reserva reserva = reservaRepository.getReferenceById(id);
        return reservaMapper.toReservaDTO(reserva);
    }

    @Override
    public ReservaDTO actualizarReserva(ReservaDTO reservaDTO) {
        return crearReserva(reservaDTO);
    }

    @Override
    public boolean eliminarReserva(ReservaDTO reservaDTO) {
        Reserva reserva = reservaRepository.getReferenceById(reservaDTO.getId());
        Long idReserva = reserva.getId();
        reservaRepository.delete(reserva);
        return reservaRepository.findById(idReserva).isEmpty();
    }
}
