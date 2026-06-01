package org.example.cursospring.rapidito.api.controller;

import jakarta.validation.Valid;
import org.example.cursospring.rapidito.api.dto.ClienteDTO;
import org.example.cursospring.rapidito.api.service.interfaces.IClienteService;
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

    @GetMapping("/")
    public ResponseEntity<List<ClienteDTO>> mostrarClientes() {
        return ResponseEntity.ok(clienteService.mostrarClientes());
    }

    @PostMapping("/")
    public ResponseEntity<ClienteDTO> guardarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.crearCliente(clienteDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> mostrarCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.mostrarCliente(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable Long id,
                                                        @Valid @RequestBody ClienteDTO clienteDTO) {
        clienteDTO.setId(id);
        return ResponseEntity.ok(clienteService.actualizarCliente(clienteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(clienteService.mostrarCliente(id));
        return ResponseEntity.noContent().build();
    }
}
