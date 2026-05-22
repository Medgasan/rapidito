package org.example.cursospring.rapidito.api.controller;

import jakarta.validation.Valid;
import org.example.cursospring.rapidito.api.dto.VehiculoDTO;
import org.example.cursospring.rapidito.api.entity.Vehiculo;
import org.example.cursospring.rapidito.api.service.IVehiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoApiController {

    private final IVehiculoService vehiculoService;

    public VehiculoApiController(IVehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @GetMapping("/")
    public ResponseEntity<List<VehiculoDTO>> mostrarVehiculos() {
        return ResponseEntity.ok(vehiculoService.mostrarVehiculos());
    }

    @GetMapping("/{marca}/list")
    public ResponseEntity<List<VehiculoDTO>> mostrarVehiculosPorMarca(@PathVariable String marca) {
        return ResponseEntity.ok(vehiculoService.mostrarVehiculosPorMarca(marca));
    }

    @PostMapping("/")
    public ResponseEntity<VehiculoDTO> guardarVehiculo(@Valid @RequestBody VehiculoDTO vehiculoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoService.crearVehiculo(vehiculoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoDTO> mostrarVehiculo(@PathVariable Long id) {
        return ResponseEntity.ok(vehiculoService.mostrarVehiculo(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VehiculoDTO> actualizarVehiculo(@PathVariable Long id,
                                                          @Valid @RequestBody VehiculoDTO vehiculoDTO) {
        vehiculoDTO.setId(id);
        return ResponseEntity.ok(vehiculoService.actualizarVehiculo(vehiculoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVehiculo(@PathVariable Long id) {
        vehiculoService.eliminarVehiculo(vehiculoService.mostrarVehiculo(id));
        return ResponseEntity.noContent().build();
    }

    //Todo: Implementar endpoint para actualizar solo el estado del vehículo
    // Verificar que el vehículo no tenga ninguna reserva activa antes de permitir el cambio de estado a "Mantenimiento"
    // Si el estado es "Mantenimiento", el vehículo no debe aparecer en la lista de vehículos disponibles para reserva.
    @PatchMapping("/{id}/estado")
    public ResponseEntity<VehiculoDTO> actualizarEstadoVehiculo(@PathVariable Long id, @RequestParam String estado) {
        VehiculoDTO vehiculoDTO = vehiculoService.mostrarVehiculo(id);
        vehiculoDTO.setEstado(Vehiculo.EstadoVehiculo.valueOf(estado));
        return ResponseEntity.ok(vehiculoService.actualizarVehiculo(vehiculoDTO));
    }
}
