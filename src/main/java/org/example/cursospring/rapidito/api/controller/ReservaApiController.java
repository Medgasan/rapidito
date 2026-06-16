package org.example.cursospring.rapidito.api.controller;

import jakarta.validation.Valid;
import org.example.cursospring.rapidito.api.dto.ContratoDTO;
import org.example.cursospring.rapidito.api.dto.ReservaDTO;
import org.example.cursospring.rapidito.api.entity.Contrato;
import org.example.cursospring.rapidito.api.entity.Reserva;
import org.example.cursospring.rapidito.api.service.interfaces.IReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaApiController {

    private final IReservaService reservaService;

    public ReservaApiController(IReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReservaDTO>> mostrarReservas() {
        return ResponseEntity.ok(reservaService.mostrarReservas());
    }

    @PostMapping("/")
    public ResponseEntity<ReservaDTO> guardarReserva(@Valid @RequestBody ReservaDTO reservaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.crearReserva(reservaDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> mostrarReserva(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.mostrarReserva(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ReservaDTO> actualizarReserva(@PathVariable Long id,
                                                        @Valid @RequestBody ReservaDTO reservaDTO) {
        reservaDTO.setId(id);
        return ResponseEntity.ok(reservaService.actualizarReserva(reservaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id) {
        reservaService.eliminarReserva(reservaService.mostrarReserva(id));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<ReservaDTO> actualizarEstadoReserva(@PathVariable Long id, @RequestParam String estado) {
        ReservaDTO reservaDTO = reservaService.mostrarReserva(id);
        reservaDTO.setEstado(Reserva.EstadoReserva.valueOf(estado));
        return ResponseEntity.ok(reservaService.actualizarReserva(reservaDTO));
    }


    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ReservaDTO> cancelarReserva(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.cancelarReserva(id));
    }


    @GetMapping("/buscar")
    public ResponseEntity<List<ReservaDTO>> buscarReservas(@RequestParam(required = false) Long clienteId,
                                                          @RequestParam(required = false) Reserva.EstadoReserva estado,
                                                          @RequestParam(required = false) LocalDate fechaInicio,
                                                          @RequestParam(required = false) LocalDate fechaFin) {
        // Implementar lógica de búsqueda en el servicio de reservas
        List<ReservaDTO> reservas = reservaService.mostrarReservasPorFiltro(clienteId,estado,fechaInicio,fechaFin);
        return ResponseEntity.ok(reservas);
    }

}
