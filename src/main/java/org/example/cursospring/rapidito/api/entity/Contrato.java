package org.example.cursospring.rapidito.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString(exclude = {"vehiculo", "cliente"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "contrato",
    indexes = {
        @Index(name = "idx_contrato_vehiculo_fechas",columnList = "id_vehiculo, fecha_inicio, fecha_fin")
    }
)
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column private LocalDate fechaInicio;
    @Column private LocalDate fechaFin;
    @Column private int numeroDias;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioVehiculo;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalContrato;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoContrato estado = EstadoContrato.ACTIVO;

    public enum EstadoContrato { ACTIVO, CERRADO, CANCELADO }

}
