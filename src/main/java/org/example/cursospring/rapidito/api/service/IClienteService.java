package org.example.cursospring.rapidito.api.service;

import org.example.cursospring.rapidito.api.dto.ClienteDTO;

import java.util.List;

public interface IClienteService {
    ClienteDTO crearCliente(ClienteDTO clienteDTO);
    ClienteDTO actualizarCliente(ClienteDTO clienteDTO);
    ClienteDTO mostrarCliente(Long id);
    boolean eliminarCliente(ClienteDTO clienteDTO);
    List<ClienteDTO> mostrarClientes();

}
