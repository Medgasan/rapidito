package org.example.cursospring.rapidito.api.repository;

import org.example.cursospring.rapidito.api.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Long> {
}
