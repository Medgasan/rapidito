package org.example.cursospring.rapidito.api.entity.superclass;

import jakarta.persistence.*;
import lombok.Data;
import org.example.cursospring.rapidito.api.entity.Suplemento;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
public class SuplementoAsignadoBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_suplemento", nullable = false)
    private Suplemento suplemento; // Referencia al catálogo original

    @Column(name = "nombre_aplicado", nullable = false)
    private String nombreAplicado;

    @Column(name = "precio_base_aplicado", nullable = false)
    private BigDecimal precioBaseAplicado;

    @Column(name = "total_calculado", nullable = false)
    private BigDecimal totalCalculado;

    @Column(name = "unidades_calculadas", nullable = false)
    private Integer unidadesCalculadas;

}
