package org.example.cursospring.rapidito.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String direccion;
    @Column
    private String telefono;
    @Column
    private String email;


    // Relaciones
    @OneToMany(mappedBy = "cliente")
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "cliente")
    private List<Contrato> contratos;

}
