package org.example.cursospring.rapidito.service;

import org.example.cursospring.rapidito.dto.ClienteDTO;
import org.example.cursospring.rapidito.entity.Cliente;
import org.example.cursospring.rapidito.mappers.ClienteMapper;
import org.example.cursospring.rapidito.repository.ClienteRepository;
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
        cliente = clienteRepository.save(cliente);
        return clienteMapper.toClienteDTO(cliente);
    }

    @Override
    public ClienteDTO actualizarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toCliente(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return clienteMapper.toClienteDTO(cliente);
    }

    @Override
    public ClienteDTO mostrarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
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
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.toClienteDTOList(clientes);
    }
}
