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

}
