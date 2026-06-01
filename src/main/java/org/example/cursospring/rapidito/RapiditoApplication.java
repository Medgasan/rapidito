package org.example.cursospring.rapidito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RapiditoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RapiditoApplication.class, args);
    }


    // Backlog general:
    // Todo: - Añadir documentación de la API con Swagger/OpenAPI
    // Todo:  - Implementar seguridad con Spring Security (JWT)
    // - Añadir logging con SLF4J y Logback - Revisar si ya no hay problemas de seguridad o buscar alternativa actual
    // Todo:  - Implementar pruebas unitarias con JUnit y Mockito
    // Todo:  - Implementar pruebas de integración con Spring Boot Test

}
