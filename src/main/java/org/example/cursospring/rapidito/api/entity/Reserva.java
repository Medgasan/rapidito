package org.example.cursospring.rapidito.api.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.cursospring.rapidito.api.entity.embedded.DatosConductor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = {"vehiculo", "cliente", "conductoresAdicionales"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "reserva",
        indexes = {
                @Index(name = "idx_reserva_vehiculo_fechas", columnList = "id_vehiculo, fecha_inicio, fecha_fin")
        }
)
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true)
    private String slug;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehiculo")
    private Vehiculo vehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    /**
     * CONTEXTO LEGAL ESPAÑA: Datos de la persona que conducirá principalmente el coche.
     * Mapea los campos directamente en la tabla 'reserva'.
     */
    @Embedded
    private DatosConductor conductorHabitual;

    /**
     * Lista de conductores adicionales autorizados en el seguro para esta reserva.
     * cascade = CascadeType.ALL permite guardar los conductores automáticamente al guardar la reserva.
     */
    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConductorAdicional> conductoresAdicionales = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Reserva.EstadoReserva estado = EstadoReserva.PENDIENTE;

    public enum EstadoReserva { PENDIENTE, CONFIRMADA, CANCELADA, COMPLETADA }

    @OneToMany(mappedBy = "reserva")
    private List<ReservaSuplemento>  suplemento;



    // --- Métodos Helper para sincronizar la relación bidireccional de forma segura ---
    public void addConductorAdicional(ConductorAdicional conductor) {
        this.conductoresAdicionales.add(conductor);
        conductor.setReserva(this);
    }

    public void removeConductorAdicional(ConductorAdicional conductor) {
        this.conductoresAdicionales.remove(conductor);
        conductor.setReserva(null);
    }
}