package org.example.cursospring.rapidito.api.controller;

import jakarta.validation.Valid;
import org.example.cursospring.rapidito.api.dto.ContratoDTO;
import org.example.cursospring.rapidito.api.entity.Contrato;
import org.example.cursospring.rapidito.api.service.interfaces.IContratoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/contratos")
public class ContratoApiController {

    private final IContratoService contratoService;

    public ContratoApiController(IContratoService contratoService) {
        this.contratoService = contratoService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ContratoDTO>> mostrarContratos() {
        return ResponseEntity.ok(contratoService.mostrarContratos());
    }

    @PostMapping("/")
    public ResponseEntity<ContratoDTO> guardarContrato(@Valid @RequestBody ContratoDTO contratoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contratoService.crearContrato(contratoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratoDTO> mostrarContrato(@PathVariable Long id) {
        return ResponseEntity.ok(contratoService.mostrarContrato(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ContratoDTO> actualizarContrato(@PathVariable Long id,
                                                          @Valid @RequestBody ContratoDTO contratoDTO) {
        contratoDTO.setId(id);
        return ResponseEntity.ok(contratoService.actualizarContrato(contratoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarContrato(@PathVariable Long id) {
        contratoService.eliminarContrato(contratoService.mostrarContrato(id));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/desde-reserva/{id}")
    public ResponseEntity<ContratoDTO> crearContratoDesdeReserva(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contratoService.crearContratoDesdeReserva(id));
    }

    @PatchMapping("/{id}/cerrar")
    public ResponseEntity<ContratoDTO> cerrarContrato(@PathVariable Long id) {
        return ResponseEntity.ok(contratoService.cerrarContrato(id));
    }


    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ContratoDTO> cancelarContrato(@PathVariable Long id) {
        return ResponseEntity.ok(contratoService.cancelarContrato(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ContratoDTO>> buscarContratos(@RequestParam(required = false) Long clienteId,
                                              @RequestParam(required = false) Long vehiculoId,
                                              @RequestParam(required = false) Contrato.EstadoContrato estado,
                                              @RequestParam(required = false) LocalDate fechaInicio,
                                              @RequestParam(required = false) LocalDate fechaFin) {
        // Implementar lógica de búsqueda en el servicio de contratos
        List<ContratoDTO> contratos = contratoService.mostrarContratosPorFiltro(clienteId,vehiculoId,estado,fechaInicio,fechaFin);
        return ResponseEntity.ok(contratos);
    }

}
