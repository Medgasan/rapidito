package org.example.cursospring.rapidito.api.controller;


import org.example.cursospring.rapidito.api.dto.ClienteDTO;
import org.example.cursospring.rapidito.api.service.IClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteApiController {


    private final IClienteService clienteService;


    public ClienteApiController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }


    // mostrar todos
    @GetMapping("/")
    public ResponseEntity<List<ClienteDTO>> mostrarClientes(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(clienteService.mostrarClientes());
    }


    // Crear - guardar datos
    @PostMapping("/")
    public ResponseEntity<ClienteDTO> guardarCliente(@RequestBody ClienteDTO clienteDTO){
        clienteDTO = clienteService.crearCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO);
    }


    // ver
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> mostrarCliente(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.mostrarCliente(id));
    }


    // editar/actualizar
    @PostMapping("/{id}/edit")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
        clienteDTO = clienteService.actualizarCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO);
    }


    // borrar
    @GetMapping("/{id}/delete")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id){
        boolean resultado = clienteService.eliminarCliente(clienteService.mostrarCliente(id));
        return ResponseEntity.ok().build();
    }


}
