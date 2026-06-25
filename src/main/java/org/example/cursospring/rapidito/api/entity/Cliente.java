package org.example.cursospring.rapidito.api.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.cursospring.rapidito.api.entity.embedded.DatosConductor;

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

    @Column(unique = true)
    private String slug;
    @Column private String direccion;
    @Column private String telefono;
    @Column private String email;
    @Column private String sexo;
    @Column private String nacionalidad;

    @Embedded
    private DatosConductor datosConductor;

    @OneToMany(mappedBy = "cliente")
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "cliente")
    private List<Contrato> contratos;

}
