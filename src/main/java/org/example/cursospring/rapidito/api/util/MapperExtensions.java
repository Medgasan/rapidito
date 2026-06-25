package org.example.cursospring.rapidito.api.util;

import lombok.experimental.UtilityClass;
import org.example.cursospring.rapidito.api.dto.*;
import org.example.cursospring.rapidito.api.entity.*;
import org.example.cursospring.rapidito.api.entity.embedded.DatosConductor;
import org.example.cursospring.rapidito.api.mappers.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@UtilityClass
public class MapperExtensions {


    private static final ClienteMapper clienteMapper = Mappers.getMapper(ClienteMapper.class);
    private static final ConductorAdicionalMapper conductorAdicionalMapper = Mappers.getMapper(ConductorAdicionalMapper.class);
    private static final ContratoMapper contratoMapper = Mappers.getMapper(ContratoMapper.class);
    private static final DatosConductorMapper datosConductorMapper = Mappers.getMapper(DatosConductorMapper.class);
    private static final ReservaMapper reservaMapper = Mappers.getMapper(ReservaMapper.class);
    private static final VehiculoMapper vehiculoMapper = Mappers.getMapper(VehiculoMapper.class);


    public static ClienteDTO toDTO(Cliente entity) {
        if (entity == null) {
            return null;
        }
        return clienteMapper.toDTO(entity);
    }

    public static Cliente toEntity(ClienteDTO dto) {
        if (dto == null) {
            return null;
        }
        return clienteMapper.toEntity(dto);
    }

    public static List<ClienteDTO> toClienteDTOList(List<Cliente> entities) {
        if (entities == null) {
            return null;
        }
        return clienteMapper.toDTOList(entities);
    }

    public static List<Cliente> toClienteEntityList(List<ClienteDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return clienteMapper.toEntityList(dtos);
    }



    public static ContratoDTO toDTO(Contrato entity) {
        if (entity == null) {
            return null;
        }
        return contratoMapper.toDTO(entity);
    }

    public static Contrato toEntity(ContratoDTO dto) {
        if (dto == null) {
            return null;
        }
        return contratoMapper.toEntity(dto);
    }

    public static List<ContratoDTO> toContratoDTOList(List<Contrato> entities) {
        if (entities == null) {
            return null;
        }
        return contratoMapper.toDTOList(entities);
    }

    public static List<Contrato> toContratoEntityList(List<ContratoDTO> DTOs) {
        if (DTOs == null) {
            return null;
        }
        return contratoMapper.toEntityList(DTOs);
    }



    public static ConductorAdicionalDTO toDTO(ConductorAdicional entity) {
        if (entity == null) {
            return null;
        }
        return conductorAdicionalMapper.toDTO(entity);
    }

    public static ConductorAdicional toEntity(ConductorAdicionalDTO dto) {
        if (dto == null) {
            return null;
        }
        return conductorAdicionalMapper.toEntity(dto);
    }

    public static List<ConductorAdicionalDTO> toConductorAdicionalDTOList(List<ConductorAdicional> entities) {
        if (entities == null) {
            return null;
        }
        return conductorAdicionalMapper.toDTOList(entities);
    }

    public static List<ConductorAdicional> toConductorAdicionalEntityList(List<ConductorAdicionalDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return conductorAdicionalMapper.toEntityList(dtos);
    }



    public static DatosConductorDTO toDatosConductorDTO(DatosConductor datosConductor) {
        if (datosConductor == null) {
            return null;
        }
        return datosConductorMapper.toDTO(datosConductor);
    }

    public static DatosConductor toDatosConductor(DatosConductorDTO datosConductorDTO) {
        if (datosConductorDTO == null) {
            return null;
        }
        return datosConductorMapper.toEntity(datosConductorDTO);
    }

    public static List<DatosConductorDTO> toDatosConductorDTOList(List<DatosConductor> entities) {
        if (entities == null) {
            return null;
        }
        return datosConductorMapper.toDTOList(entities);
    }

    public static List<DatosConductor> toDatosConductorEntityList(List<DatosConductorDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return datosConductorMapper.toEntityList(dtos);
    }



    public static ReservaDTO toDTO(Reserva reserva) {
        if (reserva == null) {
            return null;
        }
        return reservaMapper.toDTO(reserva);
    }

    public static Reserva toEntity(ReservaDTO reservaDTO) {
        if (reservaDTO == null) {
            return null;
        }
        return reservaMapper.toEntity(reservaDTO);
    }

    public static List<ReservaDTO> toReservaDTOList(List<Reserva> reservas) {
        if (reservas == null) {
            return null;
        }
        return reservaMapper.toDTOList(reservas);
    }

    public static List<Reserva> toReservaEntityList(List<ReservaDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return reservaMapper.toEntityList(dtos);
    }



    public static VehiculoDTO toDTO(Vehiculo vehiculo) {
        if (vehiculo == null) {
            return null;
        }
        return vehiculoMapper.toEntityDTO(vehiculo);
    }

    public static Vehiculo toEntity(VehiculoDTO vehiculoDTO) {
        if (vehiculoDTO == null) {
            return null;
        }
        return vehiculoMapper.toEntity(vehiculoDTO);
    }

    public static List<VehiculoDTO> toVehiculoDTOList(List<Vehiculo> entity) {
        if (entity == null) {
            return null;
        }
        return vehiculoMapper.toDTOList(entity);
    }

    public static List<Vehiculo> toVehiculoEntityList(List<VehiculoDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return vehiculoMapper.toEntityList(dtos);
    }

}