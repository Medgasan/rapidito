package org.example.cursospring.rapidito.api.mappers;

import org.example.cursospring.rapidito.api.dto.ClienteDTO;
import org.example.cursospring.rapidito.api.entity.Cliente;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toCliente(ClienteDTO clienteDTO);
    ClienteDTO toClienteDTO(Cliente cliente);
    List<ClienteDTO> toClienteDTOList(List<Cliente> clientes);
    List<Cliente> toClientesList(List<ClienteDTO> clienteDTOs);

}
