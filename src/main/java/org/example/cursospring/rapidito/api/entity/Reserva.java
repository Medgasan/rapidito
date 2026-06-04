package org.example.cursospring.rapidito.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString(exclude = {"vehiculo", "cliente"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "reserva",
    indexes = {
        @Index(name = "idx_reserva_vehiculo_fechas",columnList = "id_vehiculo, fecha_inicio, fecha_fin")
    }
)
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true)
    private String slug;

    @Column private LocalDate fechaInicio;
    @Column private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Reserva.EstadoReserva estado = EstadoReserva.PENDIENTE;

    public enum EstadoReserva  { PENDIENTE, CONFIRMADA, CANCELADA, COMPLETADA }

    @PrePersist
    private void generateSlug() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        this.slug = (cliente.getNombre() + "-" + vehiculo.getModelo()).toLowerCase()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-|-$", "") // trim guiones
                + "-" + uuid;
    }

}
