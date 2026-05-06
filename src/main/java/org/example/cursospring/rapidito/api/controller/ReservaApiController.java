package org.example.cursospring.rapidito.api.controller;


import org.example.cursospring.rapidito.api.dto.ReservaDTO;
import org.example.cursospring.rapidito.api.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaApiController {

    private final ReservaService reservaService;

    public ReservaApiController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }


    @GetMapping("/")
    public ResponseEntity<List<ReservaDTO>> mostrarReservas(){
        return ResponseEntity.status(HttpStatus.OK).body(reservaService.mostrarReservas());
    }


//    // Crear - obtener datos
//    @GetMapping("/new")
//    public String nuevoReserva(Model model){
//        model.addAttribute("cliente", new ReservaDTO());
//        model.addAttribute("editMode", true);
//        return "reserva";
//    }


    // Crear - guardar datos
    @PostMapping("/{id}")
    public ResponseEntity<ReservaDTO> guardarReserva(@RequestBody ReservaDTO reservaDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.crearReserva(reservaDTO));
    }


    // ver
    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> mostrarReserva(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(reservaService.mostrarReserva(id));
    }


    // editar/actualizar
    @GetMapping("/{id}/edit")
    public ResponseEntity<ReservaDTO> editarReserva(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(reservaService.mostrarReserva(id));
    }


    // editar/actualizar
    @PostMapping("/{id}/edit")
    public ResponseEntity<ReservaDTO> actualizarReserva(@RequestBody ReservaDTO reservaDTO){
        return ResponseEntity.status(HttpStatus.OK).body(reservaService.actualizarReserva(reservaDTO));
    }


    // borrar
    @GetMapping("/{id}/delete")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id){
        boolean deleted = reservaService.eliminarReserva(reservaService.mostrarReserva(id));
        return ResponseEntity.ok().build();
    }



}
