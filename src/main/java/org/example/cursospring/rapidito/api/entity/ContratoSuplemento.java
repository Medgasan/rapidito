package org.example.cursospring.rapidito.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.cursospring.rapidito.api.entity.embedded.AuditoriaAgentes;
import org.example.cursospring.rapidito.api.entity.superclass.SuplementoAsignadoBase;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Data
@Entity
@Audited
public class ContratoSuplemento extends SuplementoAsignadoBase {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contrato", nullable = false) // ¡NOT NULL absoluto!
    private Contrato contrato;

    @Embedded
    @NotAudited
    AuditoriaAgentes trazabilidad;

}
