package org.example.cursospring.rapidito.api.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.cursospring.rapidito.api.dto.ReservaDTO;
import org.example.cursospring.rapidito.api.entity.Reserva;
import org.example.cursospring.rapidito.api.exception.EstadoInvalidoException;
import org.example.cursospring.rapidito.api.exception.VehiculoNoDisponibleException;
import org.example.cursospring.rapidito.api.mappers.ReservaMapper;
import org.example.cursospring.rapidito.api.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservaService implements IReservaService {

    private final ReservaRepository reservaRepository;
    private final VehiculoService vehiculoService;
    private final ReservaMapper reservaMapper;

    public ReservaService(ReservaRepository reservaRepository, VehiculoService vehiculoService, ReservaMapper reservaMapper) {
        this.reservaRepository = reservaRepository;
        this.vehiculoService = vehiculoService;
        this.reservaMapper = reservaMapper;
    }

    @Override
    public List<ReservaDTO> mostrarReservas() {
        return reservaMapper.toReservaDTOList(reservaRepository.findAll());
    }

    @Override
    public ReservaDTO crearReserva(ReservaDTO reservaDTO) {
        if (!vehiculoService.isDisponible(
                reservaDTO.getVehiculo().getId(),
                reservaDTO.getFechaInicio(),
                reservaDTO.getFechaFin())) {
            throw new VehiculoNoDisponibleException(
                    "Vehículo no disponible en el periodo solicitado");
        }
        Reserva reserva = reservaMapper.toReserva(reservaDTO);
        return reservaMapper.toReservaDTO(reservaRepository.save(reserva));
    }

    @Override
    public ReservaDTO mostrarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada: " + id));
        return reservaMapper.toReservaDTO(reserva);
    }

    @Override
    public ReservaDTO actualizarReserva(ReservaDTO reservaDTO) {
        reservaRepository.findById(reservaDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada: " + reservaDTO.getId()));
        return reservaMapper.toReservaDTO(reservaRepository.save(reservaMapper.toReserva(reservaDTO)));
    }

    @Override
    public boolean eliminarReserva(ReservaDTO reservaDTO) {
        Reserva reserva = reservaRepository.findById(reservaDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada: " + reservaDTO.getId()));
        Long idReserva = reserva.getId();
        reservaRepository.delete(reserva);
        return reservaRepository.findById(idReserva).isEmpty();
    }

    @Override
    public ReservaDTO cancelarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada: " + id));
        if (reserva.getEstado() == Reserva.EstadoReserva.COMPLETADA) {
            throw new EstadoInvalidoException("No se puede cancelar una reserva completada");
        }
        reserva.setEstado(Reserva.EstadoReserva.CANCELADA);
        return reservaMapper.toReservaDTO(reservaRepository.save(reserva));
    }
}
