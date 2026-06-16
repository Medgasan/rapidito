package org.example.cursospring.rapidito.api.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.experimental.ExtensionMethod;
import org.example.cursospring.rapidito.api.dto.ConductorAdicionalDTO;
import org.example.cursospring.rapidito.api.dto.ContratoDTO;
import org.example.cursospring.rapidito.api.entity.Contrato;
import org.example.cursospring.rapidito.api.entity.Reserva;
import org.example.cursospring.rapidito.api.exception.EstadoInvalidoException;
import org.example.cursospring.rapidito.api.repository.ContratoRepository;
import org.example.cursospring.rapidito.api.repository.ReservaRepository;
import org.example.cursospring.rapidito.api.service.interfaces.IContratoService;
import org.example.cursospring.rapidito.api.service.validation.ReglaValidacionConductor;
import org.example.cursospring.rapidito.api.util.MapperExtensions;
import org.example.cursospring.rapidito.api.util.SlugGenerator;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@ExtensionMethod({MapperExtensions.class})
public class ContratoService implements IContratoService {

    private final ContratoRepository contratoRepository;
    private final ReservaRepository reservaRepository;
    private final SlugGenerator slugGenerator;
    private final List<ReglaValidacionConductor> reglasValidacion;


    public ContratoService(ContratoRepository contratoRepository,
                           ReservaRepository reservaRepository,
                           SlugGenerator slugGenerator, List<ReglaValidacionConductor> reglasValidacion) {
        this.contratoRepository = contratoRepository;
        this.reservaRepository = reservaRepository;
        this.slugGenerator = slugGenerator;
        this.reglasValidacion = reglasValidacion;
    }

    @Override
    public List<ContratoDTO> mostrarContratos() {
        return contratoRepository.findAll().toContratoDTOList();
    }

    @Override
    public ContratoDTO crearContrato(ContratoDTO contratoDTO) {

        for (ReglaValidacionConductor regla : reglasValidacion) {
            regla.validar(contratoDTO.getConductorHabitual(),contratoDTO.getVehiculo());
            for (ConductorAdicionalDTO conductorDTO : contratoDTO.getConductoresAdicionales()) {
                regla.validar(conductorDTO.getDatosConductor(),contratoDTO.getVehiculo());
            }
        }

        Contrato contrato = contratoDTO.toEntity();
        calcularPrecio(contrato);

        contrato.setSlug(slugGenerator.generateSlug(
                contrato.getCliente().getDatosConductor().getNombre(),
                contrato.getVehiculo().getMarca(),
                contrato.getVehiculo().getModelo())
        );
        return (contratoRepository.save(contrato)).toDTO();
    }

    @Override
    public ContratoDTO mostrarContrato(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrato no encontrado: " + id));
        return contrato.toDTO();
    }

    @Override
    public ContratoDTO actualizarContrato(ContratoDTO contratoDTO) {
        contratoRepository.findById(contratoDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Contrato no encontrado: " + contratoDTO.getId()));
        return crearContrato(contratoDTO);
    }

    @Override
    public void eliminarContrato(ContratoDTO contratoDTO) {
        Contrato contrato = contratoDTO.toEntity();
        Long id = contrato.getId();
        contratoRepository.delete(contrato);
        contratoRepository.findById(id);
    }

    @Override
    public ContratoDTO crearContratoDesdeReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada: " + id));
        if (reserva.getEstado() != Reserva.EstadoReserva.CONFIRMADA) {
            throw new EstadoInvalidoException("La reserva debe estar CONFIRMADA para crear un contrato");
        }
        Contrato contrato = new Contrato();
        contrato.setVehiculo(reserva.getVehiculo());
        contrato.setCliente(reserva.getCliente());
        contrato.setFechaInicio(reserva.getFechaInicio());
        contrato.setFechaFin(reserva.getFechaFin());
        contrato.setEstado(Contrato.EstadoContrato.ACTIVO);
        contrato.setSlug(slugGenerator.generateSlug(
                contrato.getCliente().getDatosConductor().getNombre(),
                contrato.getVehiculo().getMarca(),
                contrato.getVehiculo().getModelo())
        );
        calcularPrecio(contrato);
        reserva.setEstado(Reserva.EstadoReserva.COMPLETADA);
        reservaRepository.save(reserva);

        return (contratoRepository.save(contrato)).toDTO();
    }

    @Override
    public ContratoDTO cerrarContrato(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrato no encontrado: " + id));

        if (contrato.getEstado() != Contrato.EstadoContrato.ACTIVO) {
            throw new EstadoInvalidoException("El contrato debe estar ACTIVO para cerrarlo");
        }

        contrato.setEstado(Contrato.EstadoContrato.CERRADO);
        return (contratoRepository.save(contrato)).toDTO();
    }


    @Override
    public ContratoDTO cancelarContrato(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrato no encontrado: " + id));

        if (contrato.getEstado() != Contrato.EstadoContrato.ACTIVO) {
            throw new EstadoInvalidoException("El contrato debe estar ACTIVO para Cancelarlo");
        }

        contrato.setEstado(Contrato.EstadoContrato.CANCELADO);
        return (contratoRepository.save(contrato)).toDTO();
    }

    //doit: (040626) Implementar metodo de búsqueda por filtros utilizando el repositorio y mapeando los resultados a DTOs
    @Override
    public List<ContratoDTO> mostrarContratosPorFiltro(Long clienteId, Long vehiculoId, Contrato.EstadoContrato estadoContrato, LocalDate fechaInicio, LocalDate fechaFin) {
        return (contratoRepository.findByFiltro(clienteId, vehiculoId, estadoContrato, fechaInicio, fechaFin)).toContratoDTOList();
    }


    // Metodo para calcular el precio del contrato basado en la duración y el precio diario del vehículo
    private void calcularPrecio(Contrato contrato) {
        long dias = ChronoUnit.DAYS.between(contrato.getFechaInicio(), contrato.getFechaFin());
        BigDecimal precioFinal = contrato.getVehiculo().getPrecioDia().multiply(BigDecimal.valueOf(dias));

        // TODO: - Implementar lógica de descuentos según la duración del alquiler por base de datos
//        if (dias >= 7) {
//            precioFinal *= 0.9; // 10% de descuento para alquileres de 7 días o más
//        } else if (dias >= 3) {
//            precioFinal *= 0.95; // 5% de descuento para alquileres de 3 a 6 días
//        }

        contrato.setTotalContrato(precioFinal.setScale(2, RoundingMode.HALF_UP));
    }
}
