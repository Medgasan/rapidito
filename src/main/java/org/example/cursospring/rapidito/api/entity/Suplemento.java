package org.example.cursospring.rapidito.api.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Suplemento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "precio_base", nullable = false)
    private BigDecimal precioBase;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_calculo", nullable = false)
    private TipoCalculo tipoCalculo; // POR_DIA, FIJO

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_asignacion", nullable = false)
    private TipoAsignacion tipoAsignacion; // MANUAL, AUTOMATICO

    @Column(name = "condicion_activacion")
    private String condicionActivacion; // Expresión SpEL, ej: "conductorHabitual.edad < 25"

    private boolean activo = true;

    public enum TipoCalculo { POR_DIA, FIJO }
    public enum TipoAsignacion { MANUAL, AUTOMATICO }

}
