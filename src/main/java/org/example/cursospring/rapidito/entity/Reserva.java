package org.example.cursospring.rapidito.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate fechaInicio;

    @Column
    private LocalDate fechaFin;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo;


    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

}
