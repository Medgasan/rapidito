package org.example.cursospring.rapidito.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.example.cursospring.rapidito.api.entity.superclass.SuplementoAsignadoBase;

@Data
@Entity
public class ReservaSuplemento extends SuplementoAsignadoBase {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reserva", nullable = false) // ¡NOT NULL absoluto!
    private Reserva reserva;
}
