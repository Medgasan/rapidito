package org.example.cursospring.rapidito.api.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.cursospring.rapidito.api.dto.ClienteDTO;
import org.example.cursospring.rapidito.api.entity.Cliente;
import org.example.cursospring.rapidito.api.mappers.ClienteMapper;
import org.example.cursospring.rapidito.api.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toCliente(clienteDTO);
        return clienteMapper.toClienteDTO(clienteRepository.save(cliente));
    }

    @Override
    public ClienteDTO actualizarCliente(ClienteDTO clienteDTO) {
        clienteRepository.findById(clienteDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado: " + clienteDTO.getId()));
        return crearCliente(clienteDTO);
    }

    @Override
    public ClienteDTO mostrarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado: " + id));
        return clienteMapper.toClienteDTO(cliente);
    }

    @Override
    public boolean eliminarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toCliente(clienteDTO);
        Long id = cliente.getId();
        clienteRepository.delete(cliente);
        return clienteRepository.findById(id).isEmpty();
    }

    @Override
    public List<ClienteDTO> mostrarClientes() {
        return clienteMapper.toClienteDTOList(clienteRepository.findAll());
    }
}
