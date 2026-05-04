package org.example.cursospring.rapidito.repository;

import org.example.cursospring.rapidito.entity.Cliente;
import org.example.cursospring.rapidito.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Long> {
}
