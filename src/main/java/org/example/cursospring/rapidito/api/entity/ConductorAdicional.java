package org.example.cursospring.rapidito.api.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.example.cursospring.rapidito.api.entity.embedded.DatosConductor;


@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ConductorAdicional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Embedded // La base de datos creará los mismos campos de carnet aquí automáticamente
    private DatosConductor datosConductor;

    /**
     * Relación con Reserva (Opcional, null si se añade directo en el contrato)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reserva", nullable = true)
    private Reserva reserva;

    /**
     * Relación con Contrato (Opcional, null si la reserva aún no es contrato)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contrato", nullable = true)
    private Contrato contrato;



}
