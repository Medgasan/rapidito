package org.example.cursospring.rapidito.api.entity.embedded;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Embeddable
@Getter
@Setter
public class DatosConductor {

    @Column(name = "tipo_documento") private String tipoDocumento;
    @Column(name = "numero_documento") private String numeroDocumento;
    @Column(name = "nombre") private String nombre;
    @Column(name = "apellido") private String apellido;
    @Column(name = "tipo_carnet") private String tipoCarnet;
    @Column(name = "numero_carnet") private String numeroCarnet;
    @Column(name = "validez_carnet") private String validezCarnet;
    @Column(name = "fecha_obtencion")  private LocalDate fechaObtencion;
    @Column(name = "numero_soporte_carnet") private String numeroSoporteCarnet; // Obligatorio DGT
    @Column(name = "fecha_nacimiento") private LocalDate fechaNacimiento; // Para controlar edad mínima del seguro
}
