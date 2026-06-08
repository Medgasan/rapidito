package org.example.cursospring.rapidito.api.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SlugGenerator {

    /**
     * Genera un slug basado en múltiples cadenas de texto.
     * Al recibir un varargs (String...), sirve igual para Reserva (Cliente, Vehiculo)
     * que para Contrato o cualquier otra entidad futura.
     */
    public String generateSlug(String... parts) {
        String base = String.join("-", parts);

        // Si nos pasan nulos o vacíos, creamos un fallback genérico
        if (base.isBlank() || base.equals("-")) {
            base = "doc";
        }

        String uuid = UUID.randomUUID().toString().substring(0, 8);

        return base.toLowerCase()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-|-$", "")
                + "-" + uuid;
    }
}
