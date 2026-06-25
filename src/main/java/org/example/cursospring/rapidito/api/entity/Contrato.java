package org.example.cursospring.rapidito.api.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.cursospring.rapidito.api.entity.embedded.DatosConductor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
// Añadimos 'conductoresAdicionales' al exclusor del ToString para evitar LazyInitializationException
@ToString(exclude = {"vehiculo", "cliente", "conductoresAdicionales"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "contrato",
        indexes = {
                @Index(name = "idx_contrato_vehiculo_fechas", columnList = "id_vehiculo, fecha_inicio, fecha_fin")
        }
)
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true)
    private String slug;

    @Column private LocalDate fechaInicio;
    @Column private LocalDate fechaFin;
    @Column private int numeroDias;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioVehiculo;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalContrato;

    @Column(precision = 10, scale = 2)
    private BigDecimal fianzaRetenida; // Importante para el cumplimiento legal en España

    /**
     * CONTEXTO LEGAL ESPAÑA: El conductor principal real que firma el contrato.
     * Puede coincidir con el cliente o ser un tercero designado en el mostrador.
     */
    @Embedded
    private DatosConductor conductorHabitual;

    /**
     * Conductores adicionales autorizados específicamente en las pólizas de este contrato.
     */
    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConductorAdicional> conductoresAdicionales = new ArrayList<>();

    /**
     * Gestión del Seguro: Mapeamos el tipo de cobertura contratada mediante un Enum.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoSeguro tipoSeguro = TipoSeguro.A_TERCEROS;

    @ManyToOne(fetch = FetchType.LAZY) // Añadido LAZY por rendimiento
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo;

    @ManyToOne(fetch = FetchType.LAZY) // Añadido LAZY por rendimiento
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoContrato estado = EstadoContrato.ACTIVO;

    @OneToMany(mappedBy = "Contrato")
    private List<ContratoSuplemento>  suplemento;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reserva", nullable = true, unique = true)
    private Reserva reservaOriginal;




    // --------------------------------------------------------------------------------
    // Enums del dominio
    public enum EstadoContrato { ACTIVO, CERRADO, CANCELADO }

    public enum TipoSeguro {
        A_TERCEROS,
        TERCEROS_AMPLIADO,
        TODO_RIESGO_CON_FRANQUICIA,
        TODO_RIESGO_PREMIUM
    }

    // --- Métodos Helper para mantener la consistencia bidireccional de los conductores adicionales ---
    public void addConductorAdicional(ConductorAdicional conductor) {
        this.conductoresAdicionales.add(conductor);
        conductor.setContrato(this);
    }

    public void removeConductorAdicional(ConductorAdicional conductor) {
        this.conductoresAdicionales.remove(conductor);
        conductor.setContrato(null);
    }

    public void addSuplemento(ContratoSuplemento contratoSuplemento) {
        this.suplemento.add(contratoSuplemento);
        contratoSuplemento.setContrato(this);
    }

    public void removeSuplemento(ContratoSuplemento contratoSuplemento) {
        this.suplemento.remove(contratoSuplemento);
        contratoSuplemento.setContrato(null);
    }

    public void calcularTotalContrato() {
        BigDecimal total = (this.precioVehiculo != null) ? this.precioVehiculo : BigDecimal.ZERO;

        if (this.suplemento != null) {
            BigDecimal totalSuplementos = this.suplemento.stream()
                    .map(ContratoSuplemento::getTotalCalculado)
                    .filter(java.util.Objects::nonNull) // Evitamos NullPointerException si algún suplemento no se calculó
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            total = total.add(totalSuplementos);
        }

        this.totalContrato = total;
    }

}