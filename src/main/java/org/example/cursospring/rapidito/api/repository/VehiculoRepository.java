package org.example.cursospring.rapidito.api.repository;

import org.example.cursospring.rapidito.api.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository  extends JpaRepository<Vehiculo,Long> {
    List<Vehiculo> findVehiculoByMarca(String marca);
}
