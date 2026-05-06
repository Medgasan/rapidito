package org.example.cursospring.rapidito.api.mappers;

import org.example.cursospring.rapidito.api.dto.ClienteDTO;
import org.example.cursospring.rapidito.api.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toCliente(ClienteDTO clienteDTO);

    List<Cliente> toClientesList(List<ClienteDTO> clienteDTOs);

    @Mapping(target = "reservas", ignore = true)
    @Mapping(target = "contratos", ignore = true)
    ClienteDTO toClienteDTO(Cliente cliente);

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "reservas", ignore = true)
//    @Mapping(target = "contrato", ignore = true)
    List<ClienteDTO> toClienteDTOList(List<Cliente> clientes);


}
