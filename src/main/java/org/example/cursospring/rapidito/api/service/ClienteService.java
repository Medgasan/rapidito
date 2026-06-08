package org.example.cursospring.rapidito.api.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.cursospring.rapidito.api.dto.ClienteDTO;
import org.example.cursospring.rapidito.api.dto.ClienteHistorialDTO;
import org.example.cursospring.rapidito.api.dto.ContratoDTO;
import org.example.cursospring.rapidito.api.dto.ReservaDTO;
import org.example.cursospring.rapidito.api.entity.Cliente;
import org.example.cursospring.rapidito.api.entity.Contrato;
import org.example.cursospring.rapidito.api.mappers.ClienteMapper;
import org.example.cursospring.rapidito.api.mappers.ContratoMapper;
import org.example.cursospring.rapidito.api.mappers.ReservaMapper;
import org.example.cursospring.rapidito.api.repository.ClienteRepository;
import org.example.cursospring.rapidito.api.repository.ContratoRepository;
import org.example.cursospring.rapidito.api.repository.ReservaRepository;
import org.example.cursospring.rapidito.api.service.interfaces.IClienteService;
import org.example.cursospring.rapidito.api.util.SlugGenerator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;
    private final ReservaRepository reservaRepository;
    private final ContratoRepository contratoRepository;
    private final ClienteMapper clienteMapper;
    private final ReservaMapper reservaMapper;
    private final ContratoMapper contratoMapper;
    private final SlugGenerator slugGenerator;

    public ClienteService(
            ClienteRepository clienteRepository,
            ReservaRepository reservaRepository,
            ContratoRepository contratoRepository,
            ClienteMapper clienteMapper, ReservaMapper reservaMapper, ContratoMapper contratoMapper, SlugGenerator slugGenerator) {
        this.clienteRepository = clienteRepository;
        this.reservaRepository = reservaRepository;
        this.contratoRepository = contratoRepository;
        this.clienteMapper = clienteMapper;
        this.reservaMapper = reservaMapper;
        this.contratoMapper = contratoMapper;
        this.slugGenerator = slugGenerator;
    }

    @Override
    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toCliente(clienteDTO);
        cliente.setSlug(slugGenerator.generateSlug(cliente.getNombre(), cliente.getApellido()));
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


    @Override
    public ClienteHistorialDTO mostrarHistorialClientes(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new EntityNotFoundException("Cliente no existe"));
        List<ReservaDTO> reservas = reservaMapper.toReservaDTOList(reservaRepository.findByFiltro(clienteId, null, null, null).stream().toList()); //, pageRes);
        List<ContratoDTO> contratos = contratoMapper.toContratoDTOList(contratoRepository.findByFiltro(clienteId, null, null, null, null).stream().toList());
        long totales = contratoRepository.countByCliente_IdAndEstado(clienteId, Contrato.EstadoContrato.CERRADO);
        BigDecimal sumado = contratoRepository.sumImporteByCliente_IdAndEstado(clienteId, Contrato.EstadoContrato.CERRADO);
        return new ClienteHistorialDTO(
                clienteMapper.toClienteDTO(cliente),
                reservas,
                contratos,
                totales,
                sumado
        );
    }
}
