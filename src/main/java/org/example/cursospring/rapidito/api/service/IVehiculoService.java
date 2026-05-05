package org.example.cursospring.rapidito.api.service;

import org.example.cursospring.rapidito.api.dto.VehiculoDTO;

import java.util.List;

public interface IVehiculoService {

    List<VehiculoDTO> mostrarVehiculos();
    List<VehiculoDTO> mostrarVehiculosPorMarca(String marca);
    VehiculoDTO crearVehiculo(VehiculoDTO vehiculoDTO);
    VehiculoDTO mostrarVehiculo(Long id);
    VehiculoDTO actualizarVehiculo(VehiculoDTO vehiculoDTO);
    boolean eliminarVehiculo(VehiculoDTO vehiculoDTO);

}
