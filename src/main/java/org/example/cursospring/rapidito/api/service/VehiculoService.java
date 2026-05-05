package org.example.cursospring.rapidito.api.service;

import org.example.cursospring.rapidito.api.dto.VehiculoDTO;
import org.example.cursospring.rapidito.api.entity.Vehiculo;
import org.example.cursospring.rapidito.api.mappers.VehiculoMapper;
import org.example.cursospring.rapidito.api.repository.VehiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class VehiculoService implements IVehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final VehiculoMapper vehiculoMapper;

    public VehiculoService(VehiculoRepository vehiculoRepository, VehiculoMapper vehiculoMapper) {
        this.vehiculoRepository = vehiculoRepository;
        this.vehiculoMapper = vehiculoMapper;
    }

    
    @Override
    public List<VehiculoDTO> mostrarVehiculos() {
        List<Vehiculo> vehiculoList = vehiculoRepository.findAll();
        return vehiculoMapper.toVehiculoDTOList(vehiculoList);
    }


    @Override
    public List<VehiculoDTO> mostrarVehiculosPorMarca(@PathVariable String marca) {
        List<Vehiculo> vehiculos = vehiculoRepository.findVehiculoByMarca(marca);
        return vehiculoMapper.toVehiculoDTOList(vehiculos);
    }


    @Override
    public VehiculoDTO crearVehiculo(VehiculoDTO vehiculoDTO) {
        Vehiculo vehiculo = vehiculoMapper.toVehiculo(vehiculoDTO);
        vehiculo = vehiculoRepository.save(vehiculo);
        return vehiculoMapper.toVehiculoDTO(vehiculo);
    }

    @Override
    public VehiculoDTO mostrarVehiculo(Long id) {
        Vehiculo vehiculo = vehiculoRepository.getReferenceById(id);
        return vehiculoMapper.toVehiculoDTO(vehiculo);
    }

    @Override
    public VehiculoDTO actualizarVehiculo(VehiculoDTO vehiculoDTO) {
        return crearVehiculo(vehiculoDTO);
    }

    @Override
    public boolean eliminarVehiculo(VehiculoDTO vehiculoDTO) {
        Vehiculo vehiculo = vehiculoMapper.toVehiculo(vehiculoDTO);
        Long id = vehiculo.getId();
        vehiculoRepository.delete(vehiculo);
        return vehiculoRepository.findById(id).isEmpty();
    }
}
