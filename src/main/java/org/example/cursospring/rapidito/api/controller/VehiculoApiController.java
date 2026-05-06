package org.example.cursospring.rapidito.api.controller;


import org.example.cursospring.rapidito.api.dto.VehiculoDTO;
import org.example.cursospring.rapidito.api.service.VehiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoApiController {

    private final VehiculoService vehiculoService;

    public VehiculoApiController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }


    @GetMapping("")
    public String main(){
        return "redirect:/vehiculos/";
    }

    // mostrar todos
    @GetMapping("/")
    public ResponseEntity<List<VehiculoDTO>> mostrarVehiculos(){
        return ResponseEntity.ok(vehiculoService.mostrarVehiculos());
    }


    // mostrar por marca
    @GetMapping("/{marca}/list")
    public ResponseEntity<List<VehiculoDTO>> mostrarVehiculosPorMarca(@PathVariable String marca, Model model){
        return ResponseEntity.ok(vehiculoService.mostrarVehiculosPorMarca(marca));
    }


    // Crear - guardar datos
    @PutMapping("/")
    public ResponseEntity<VehiculoDTO> guardarVehiculo(@RequestBody VehiculoDTO vehiculoDTO){
        vehiculoDTO = vehiculoService.crearVehiculo(vehiculoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoDTO);
    }


    // ver
    @GetMapping("/{id}")
    public ResponseEntity<VehiculoDTO> mostrarVehiculo(@PathVariable Long id){
        return ResponseEntity.ok(vehiculoService.mostrarVehiculo(id));
    }


    // editar/actualizar
    @GetMapping("/{id}/edit")
    public ResponseEntity<VehiculoDTO> editarVehiculo(@PathVariable Long id){
        return ResponseEntity.ok(vehiculoService.mostrarVehiculo(id));
    }


    // editar/actualizar
    @PatchMapping("/{id}/edit")
    public ResponseEntity<VehiculoDTO> actualizarVehiculo(@RequestBody VehiculoDTO vehiculoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoDTO);
    }


    // borrar
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> eliminarVehiculo(Model model, @PathVariable Long id){
        boolean deleted = vehiculoService.eliminarVehiculo(vehiculoService.mostrarVehiculo(id));
        return ResponseEntity.ok().build();
    }

}
