package org.example.cursospring.rapidito.api.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.cursospring.rapidito.api.dto.ReservaDTO;
import org.example.cursospring.rapidito.api.entity.Reserva;
import org.example.cursospring.rapidito.api.exception.EstadoInvalidoException;
import org.example.cursospring.rapidito.api.exception.VehiculoNoDisponibleException;
import org.example.cursospring.rapidito.api.mappers.ReservaMapper;
import org.example.cursospring.rapidito.api.repository.ReservaRepository;
import org.example.cursospring.rapidito.api.service.interfaces.IReservaService;
import org.example.cursospring.rapidito.api.util.SlugGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaService implements IReservaService {

    private final ReservaRepository reservaRepository;
    private final VehiculoService vehiculoService;
    private final ReservaMapper reservaMapper;
    private final SlugGenerator slugGenerator;

    public ReservaService(ReservaRepository reservaRepository, VehiculoService vehiculoService, ReservaMapper reservaMapper, SlugGenerator slugGenerator) {
        this.reservaRepository = reservaRepository;
        this.vehiculoService = vehiculoService;
        this.reservaMapper = reservaMapper;
        this.slugGenerator = slugGenerator;
    }

    @Override
    public List<ReservaDTO> mostrarReservas() {
        return reservaMapper.toReservaDTOList(reservaRepository.findAll());
    }

    //TODO: Añadir validaciones de negocio en crearReserva: edad mínima del conductor (≥22 años en Canarias), antigüedad del carnet (≥2 años en Canarias), ITV y seguro del vehículo en vigor
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
        reserva.setSlug(slugGenerator.generateSlug(
                reserva.getCliente().getNombre(),
                reserva.getVehiculo().getMarca(),
                reserva.getVehiculo().getModelo())
        );
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

    @Override
    public List<ReservaDTO> mostrarReservasPorFiltro(Long clienteId, Reserva.EstadoReserva estadoReserva, LocalDate fechaInicio, LocalDate fechaFin) {
        return reservaMapper.toReservaDTOList(reservaRepository.findByFiltro(clienteId, estadoReserva, fechaInicio, fechaFin));
    }
}
