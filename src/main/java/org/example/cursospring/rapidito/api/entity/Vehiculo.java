package org.example.cursospring.rapidito.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString(exclude = {"reservas", "contratos"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true)
    private String slug;

    @Column(unique = true)
    private String numeroBastidor;

    @Column
    private String marca;

    @Column
    private String modelo;

    @Column
    private String matricula;

    @Column
    private String tipoCombustible;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioDia;

    @OneToMany(mappedBy = "vehiculo")
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "vehiculo")
    private List<Contrato> contratos;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoVehiculo estado = EstadoVehiculo.OPERATIVO;

    public enum EstadoVehiculo { OPERATIVO, EN_MANTENIMIENTO }

    @PrePersist
    private void generateSlug() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        this.slug = (marca + "-" + modelo).toLowerCase()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-|-$", "") // trim guiones
                + "-" + uuid;
    }

}
