package org.example.cursospring.rapidito.api.mappers;

import org.example.cursospring.rapidito.api.dto.ClienteDTO;
import org.example.cursospring.rapidito.api.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toEntity(ClienteDTO clienteDTO);

    List<Cliente> toEntityList(List<ClienteDTO> clienteDTOs);

    @Mapping(target = "reservas", ignore = true)
    @Mapping(target = "contratos", ignore = true)
    ClienteDTO toDTO(Cliente cliente);

    List<ClienteDTO> toDTOList(List<Cliente> clientes);


}
