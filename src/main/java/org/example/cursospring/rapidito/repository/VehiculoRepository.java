package org.example.cursospring.rapidito.repository;

import org.example.cursospring.rapidito.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository  extends JpaRepository<Vehiculo,Long> {
}
