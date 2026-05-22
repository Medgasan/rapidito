package org.example.cursospring.rapidito.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

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

    @Column private String nombre;
    @Column private String apellido;
    @Column private String direccion;
    @Column private String telefono;
    @Column private String email;

    @OneToMany(mappedBy = "cliente")
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "cliente")
    private List<Contrato> contratos;
}
