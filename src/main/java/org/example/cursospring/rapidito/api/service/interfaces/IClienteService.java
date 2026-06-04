package org.example.cursospring.rapidito.api.service.interfaces;

import org.example.cursospring.rapidito.api.dto.ClienteDTO;
import org.example.cursospring.rapidito.api.dto.ClienteHistorialDTO;

import java.awt.print.Pageable;
import java.util.List;

public interface IClienteService {
    ClienteDTO crearCliente(ClienteDTO clienteDTO);
    ClienteDTO actualizarCliente(ClienteDTO clienteDTO);
    ClienteDTO mostrarCliente(Long id);
    boolean eliminarCliente(ClienteDTO clienteDTO);
    List<ClienteDTO> mostrarClientes();
    ClienteHistorialDTO mostrarHistorialClientes(Long clienteId, Pageable pageRes, Pageable pageCon);

}
