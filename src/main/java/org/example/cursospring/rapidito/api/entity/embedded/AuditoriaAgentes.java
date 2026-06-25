package org.example.cursospring.rapidito.api.entity.embedded;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class AuditoriaAgentes {

    @Column(name = "id_agente_creacion", updatable = false)
    private Long idAgenteCreacion;

    @Column(name = "fecha_operacion", updatable = false)
    private LocalDateTime fechaOperacion = LocalDateTime.now();
}

