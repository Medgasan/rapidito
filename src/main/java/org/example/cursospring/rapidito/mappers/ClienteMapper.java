package org.example.cursospring.rapidito.mappers;

import org.example.cursospring.rapidito.dto.ClienteDTO;
import org.example.cursospring.rapidito.entity.Cliente;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente toCliente(ClienteDTO clienteDTO);
    ClienteDTO toClienteDTO(Cliente cliente);
    List<ClienteDTO> toClienteDTOList(List<Cliente> clientes);
    List<Cliente> toClientesList(List<ClienteDTO> clienteDTOs);
}
