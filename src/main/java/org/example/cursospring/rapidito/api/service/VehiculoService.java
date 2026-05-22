package org.example.cursospring.rapidito.api.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.cursospring.rapidito.api.dto.VehiculoDTO;
import org.example.cursospring.rapidito.api.entity.Vehiculo;
import org.example.cursospring.rapidito.api.mappers.VehiculoMapper;
import org.example.cursospring.rapidito.api.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        return vehiculoMapper.toVehiculoDTOList(vehiculoRepository.findAll());
    }

    @Override
    public List<VehiculoDTO> mostrarVehiculosPorMarca(String marca) {
        return vehiculoMapper.toVehiculoDTOList(vehiculoRepository.findVehiculoByMarca(marca));
    }

    @Override
    public VehiculoDTO crearVehiculo(VehiculoDTO vehiculoDTO) {
        Vehiculo vehiculo = vehiculoMapper.toVehiculo(vehiculoDTO);
        return vehiculoMapper.toVehiculoDTO(vehiculoRepository.save(vehiculo));
    }

    @Override
    public VehiculoDTO mostrarVehiculo(Long id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehiculo no encontrado: " + id));
        return vehiculoMapper.toVehiculoDTO(vehiculo);
    }

    @Override
    public VehiculoDTO actualizarVehiculo(VehiculoDTO vehiculoDTO) {
        vehiculoRepository.findById(vehiculoDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Vehiculo no encontrado: " + vehiculoDTO.getId()));
        return crearVehiculo(vehiculoDTO);
    }

    @Override
    public boolean eliminarVehiculo(VehiculoDTO vehiculoDTO) {
        Vehiculo vehiculo = vehiculoMapper.toVehiculo(vehiculoDTO);
        Long id = vehiculo.getId();
        vehiculoRepository.delete(vehiculo);
        return vehiculoRepository.findById(id).isEmpty();
    }


    public boolean isDisponible(Long vehiculoId, LocalDate inicio, LocalDate fin) {
        return !vehiculoRepository.findDisponibles(inicio, fin).stream()
                .anyMatch(v->v.getId().equals(vehiculoId));
    }

}
