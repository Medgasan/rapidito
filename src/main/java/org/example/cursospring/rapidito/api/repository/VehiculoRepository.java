package org.example.cursospring.rapidito.api.repository;

import org.example.cursospring.rapidito.api.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VehiculoRepository  extends JpaRepository<Vehiculo,Long> {
    List<Vehiculo> findVehiculoByMarca(String marca);

    @Query("""
    SELECT v FROM Vehiculo v
    WHERE v.estado <> 'EN_MANTENIMIENTO'
    AND v.id NOT IN (
        SELECT r.vehiculo.id FROM Reserva r
        WHERE r.estado IN ('PENDIENTE','CONFIRMADA')
        AND r.fechaInicio < :fechaFin
        AND r.fechaFin   > :fechaInicio
    )
    AND v.id NOT IN (
        SELECT c.vehiculo.id FROM Contrato c
        WHERE c.estado = 'ACTIVO'
        AND c.fechaInicio < :fechaFin
        AND c.fechaFin   > :fechaInicio
    )
""")
    List<Vehiculo> findDisponibles(
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin")    LocalDate fechaFin
    );

}
