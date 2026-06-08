package org.example.cursospring.rapidito.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString(exclude = {"reservas", "contratos"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true)
    private String slug;

    @Column private String nombre;
    @Column private String apellido;
    @Column private String direccion;
    @Column private String telefono;
    @Column private String email;
    @Column private String sexo;
    @Column private String tipoDocumento;
    @Column private String numeroDocumento;
    @Column private String nacionalidad;
    @Column private LocalDate fechaNacimiento;
    @Column private String tipoCarnet;
    @Column private String numeroCarnet;
    @Column private String validezCarnet;
    @Column private String numeroSoporteCarnet;


    @OneToMany(mappedBy = "cliente")
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "cliente")
    private List<Contrato> contratos;

}
