package org.example.cursospring.rapidito.api.entity;

import jakarta.persistence.*;
import lombok.*;
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

    @OneToMany(mappedBy = "cliente")
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "cliente")
    private List<Contrato> contratos;

    @PrePersist
    private void generateSlug() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        this.slug = (nombre + " " + apellido).toLowerCase()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-|-$", "") // trim guiones
                + "-" + uuid;
    }
}
