package org.example.cursospring.rapidito.api.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.cursospring.rapidito.api.dto.ClienteDTO;
import org.example.cursospring.rapidito.api.dto.ClienteHistorialDTO;
import org.example.cursospring.rapidito.api.dto.ContratoDTO;
import org.example.cursospring.rapidito.api.dto.ReservaDTO;
import org.example.cursospring.rapidito.api.entity.Cliente;
import org.example.cursospring.rapidito.api.mappers.ClienteMapper;
import org.example.cursospring.rapidito.api.mappers.ContratoMapper;
import org.example.cursospring.rapidito.api.mappers.ReservaMapper;
import org.example.cursospring.rapidito.api.repository.ClienteRepository;
import org.example.cursospring.rapidito.api.repository.ContratoRepository;
import org.example.cursospring.rapidito.api.repository.ReservaRepository;
import org.example.cursospring.rapidito.api.service.interfaces.IClienteService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
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

    public ClienteService(
            ClienteRepository clienteRepository,
            ReservaRepository reservaRepository,
            ContratoRepository contratoRepository,
            ClienteMapper clienteMapper, ReservaMapper reservaMapper, ContratoMapper contratoMapper) {
        this.clienteRepository = clienteRepository;
        this.reservaRepository = reservaRepository;
        this.contratoRepository = contratoRepository;
        this.clienteMapper = clienteMapper;
        this.reservaMapper = reservaMapper;
        this.contratoMapper = contratoMapper;
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


    // Todo: ( Ya en marcha... Incompleto ) Implementar lógica para mostrar el historial de un cliente, incluyendo reservas y contratos asociados
    @Override
    public List<ClienteHistorialDTO> mostrarHistorialClientes(Long clienteId, Pageable pageRes, Pageable pageCon) {
// 1. Buscas el cliente usando el repositorio limpio
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no existe"));

        // 2. Buscas sus reservas paginadas
        List<ReservaDTO> reservas = reservaMapper.toReservaDTOList(reservaRepository.findById(clienteId).stream().toList()); //, pageRes);

        // 3. Buscas sus contratos paginados
        List<ContratoDTO> contratos = contratoRepository
                .findByClienteId(clienteId, pageCon)
                .map(this::convertirAContratoDTO);

        // 4. Calculas los agregados (Count y Sum)
        long totales = contratoRepository.countByClienteIdAndEstado(clienteId, EstadoContrato.CERRADO);
        BigDecimal sumado = contratoRepository.sumImporteByClienteIdAndEstado(clienteId, EstadoContrato.CERRADO);

        // 5. Instancias tu clase DTO con todas las piezas
        return new ClienteHistorialDTO(
                clienteMapper.toClienteDTO(cliente),
                reservas,
                contratos,
                totales,
                sumado
        );
    }
    }
}
