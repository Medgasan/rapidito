package org.example.cursospring.rapidito.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @Column
    private float precioDia;

    // Relaciones

    @OneToMany(mappedBy = "vehiculo")
    private List<Reserva> reservas;


    @OneToMany(mappedBy = "vehiculo")
    private List<Contrato> contratos;

}
