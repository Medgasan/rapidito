package org.example.cursospring.rapidito.service;

import org.example.cursospring.rapidito.dto.ClienteDTO;
import org.example.cursospring.rapidito.entity.Cliente;

import java.util.List;

public interface IClienteService {
    ClienteDTO crearCliente(ClienteDTO clienteDTO);
    ClienteDTO actualizarCliente(ClienteDTO clienteDTO);
    ClienteDTO mostrarCliente(Long id);
    boolean eliminarCliente(ClienteDTO clienteDTO);
    List<ClienteDTO> mostrarClientes();

}
