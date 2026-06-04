package org.example.cursospring.rapidito;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class RapiditoApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(RapiditoApplication.class, args);
        log.info("Aplicación Rapidito iniciada correctamente."); // <-- Log de inicio de la aplicación
    }



    // Backlog general:
    // doit: (040626) - Añadir Swagger/OpenAPI (añadida dependencia en pom, http://localhost:8080/swagger-ui/index.html)
    // Todo: - Implementar seguridad con Spring Security (JWT)
    // doit: (040626) - Añadir logging con SLF4J y Logback
    // Todo: - Implementar pruebas unitarias con JUnit y Mockito
    // Todo: - Implementar pruebas de integración con Spring Boot Test
    // doit: (040626) - Sustituir ids por slug + UUID parcial para mejorar la seguridad y escalabilidad (usar @PrePersist)
    // Todo: - Adaptar las consultas para usar slug + UUID parcial

}
