package org.example.cursospring.rapidito.api.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.experimental.ExtensionMethod;
import org.example.cursospring.rapidito.api.dto.VehiculoDTO;
import org.example.cursospring.rapidito.api.entity.Vehiculo;
import org.example.cursospring.rapidito.api.mappers.VehiculoMapper;
import org.example.cursospring.rapidito.api.repository.VehiculoRepository;
import org.example.cursospring.rapidito.api.service.interfaces.IVehiculoService;
import org.example.cursospring.rapidito.api.util.MapperExtensions;
import org.example.cursospring.rapidito.api.util.SlugGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@ExtensionMethod({MapperExtensions.class})
public class VehiculoService implements IVehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final SlugGenerator slugGenerator;


    public VehiculoService(VehiculoRepository vehiculoRepository, SlugGenerator slugGenerator) {
        this.vehiculoRepository = vehiculoRepository;
        this.slugGenerator = slugGenerator;
    }

    @Override
    public List<VehiculoDTO> mostrarVehiculos() {
        return vehiculoRepository.findAll().toVehiculoDTOList();
    }

    @Override
    public List<VehiculoDTO> mostrarVehiculosPorMarca(String marca) {
        return vehiculoRepository.findVehiculoByMarca(marca).toVehiculoDTOList();
    }

    @Override
    public VehiculoDTO crearVehiculo(VehiculoDTO vehiculoDTO) {
        Vehiculo vehiculo = vehiculoDTO.toEntity();
        vehiculo.setSlug(slugGenerator.generateSlug(vehiculo.getMarca(), vehiculo.getModelo()));
        return vehiculoRepository.save(vehiculo).toDTO();
    }

    @Override
    public VehiculoDTO mostrarVehiculo(Long id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehiculo no encontrado: " + id));
        return vehiculo.toDTO();
    }

    @Override
    public VehiculoDTO actualizarVehiculo(VehiculoDTO vehiculoDTO) {
        vehiculoRepository.findById(vehiculoDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Vehiculo no encontrado: " + vehiculoDTO.getId()));
        return crearVehiculo(vehiculoDTO);
    }

    @Override
    public boolean eliminarVehiculo(VehiculoDTO vehiculoDTO) {
        Vehiculo vehiculo = vehiculoDTO.toEntity();
        Long id = vehiculo.getId();
        vehiculoRepository.delete(vehiculo);
        return vehiculoRepository.findById(id).isEmpty();
    }

    @Override
    public List<VehiculoDTO> mostrarDisponibles(LocalDate fechaInicio, LocalDate fechaFin) {
        return vehiculoRepository.findDisponibles(fechaInicio,fechaFin).toVehiculoDTOList();
    }


    public boolean isDisponible(Long vehiculoId, LocalDate inicio, LocalDate fin) {
        return vehiculoRepository.findDisponibles(inicio, fin).stream()
                .anyMatch(v->v.getId().equals(vehiculoId));
    }

}
