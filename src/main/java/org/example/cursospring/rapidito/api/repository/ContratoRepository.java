package org.example.cursospring.rapidito.api.repository;

import org.example.cursospring.rapidito.api.entity.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato,Long> {
}
