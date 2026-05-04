package org.example.cursospring.rapidito.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Contrato {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate fechaInicio;

    @Column
    private LocalDate fechaFin;

    @Column
    private int numeroDias;

    @Column
    private float precioVehiculo;

    @Column
    private float totalContrato;


    // Realaciones
    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo;


    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

}
