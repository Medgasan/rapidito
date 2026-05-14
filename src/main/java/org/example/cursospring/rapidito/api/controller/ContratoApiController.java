package org.example.cursospring.rapidito.api.controller;


import org.example.cursospring.rapidito.api.dto.ContratoDTO;
import org.example.cursospring.rapidito.api.service.ContratoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contratos")
public class ContratoApiController {

    private final ContratoService contratoService;

    public ContratoApiController(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ContratoDTO>> mostrarContratos(Model model) {
        return ResponseEntity.status(HttpStatus.OK).body(contratoService.mostrarContratos());
    }

//    // Crear - obtener datos
//    @GetMapping("/new")
//    public ResponseEntity<ContratoDTO> nuevoContrato(Model model){
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ContratoDTO());
//    }

    // Crear - guardar datos
    @PutMapping("/")
    public ResponseEntity<ContratoDTO> guardarContrato(@RequestBody ContratoDTO contratoDTO){
        contratoDTO = contratoService.crearContrato(contratoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(contratoDTO);
    }

    // ver
    @GetMapping("/{id}")
    public ResponseEntity<ContratoDTO> mostrarContrato(Model model, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(contratoService.mostrarContrato(id));
    }

    // editar/actualizar
    @PatchMapping("/{id}/edit")
    public ResponseEntity<ContratoDTO> actualizarContrato(@RequestBody ContratoDTO contratoDTO){
        return ResponseEntity.status(HttpStatus.OK).body(contratoService.actualizarContrato(contratoDTO));
    }

    // borrar
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> eliminarContrato(@PathVariable Long id){
        boolean deleted = contratoService.eliminarContrato(contratoService.mostrarContrato(id));
        return ResponseEntity.ok().build();
    }
}
