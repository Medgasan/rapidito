# Rapidito

API REST desarrollada con **Java 17 + Spring Boot 4** para gestión de datos con arquitectura MVC, persistencia JPA y validación de entidades.

---

## Stack tecnológico

| Capa | Tecnología |
|---|---|
| Lenguaje | Java 17 |
| Framework | Spring Boot 4.0.6 |
| Persistencia | Spring Data JPA + Hibernate |
| Base de datos | MySQL 8 |
| Mapeo DTO | MapStruct 1.6 |
| Reducción de boilerplate | Lombok 1.18 |
| Validación | Spring Boot Validation (Bean Validation 3) |
| Vistas | Thymeleaf |
| Build | Maven (wrapper incluido) |
| Contenedores | Docker + Docker Compose |

---

## Requisitos previos

- Java 17+
- Maven 3.9+ (o usar el wrapper incluido `./mvnw`)
- MySQL 8 (o Docker)

---

## Ejecución local

### Sin Docker

```bash
# Clonar el repositorio
git clone https://github.com/Medgasan/rapidito.git
cd rapidito

# Configurar base de datos en src/main/resources/application.properties
# spring.datasource.url=jdbc:mysql://localhost:3306/rapidito
# spring.datasource.username=tu_usuario
# spring.datasource.password=tu_password

# Compilar y ejecutar
./mvnw spring-boot:run
```

La aplicación arranca en `http://localhost:8080`

### Con Docker Compose (recomendado)

```bash
git clone https://github.com/Medgasan/rapidito.git
cd rapidito

docker compose up --build
```

Servicios levantados:
- Aplicación: `http://localhost:8080`
- MySQL: `localhost:3306`

---

## Estructura del proyecto

```
src/
├── main/
│   ├── java/org/example/cursospring/rapidito/
│   │   ├── controller/     # Controladores MVC
│   │   ├── service/        # Lógica de negocio
│   │   ├── repository/     # Repositorios JPA
│   │   ├── model/          # Entidades
│   │   ├── dto/            # Data Transfer Objects
│   │   └── mapper/         # MapStruct mappers
│   └── resources/
│       ├── templates/      # Vistas Thymeleaf
│       └── application.properties
└── test/                   # Tests unitarios e integración
```

---

## Patrones y buenas prácticas aplicadas

- **Arquitectura en capas**: separación estricta Controller → Service → Repository
- **DTOs con MapStruct**: desacoplamiento entre capa de persistencia y API
- **Bean Validation**: validación declarativa en DTOs (`@NotNull`, `@Size`, etc.)
- **Lombok**: eliminación de boilerplate (getters, setters, constructores)
- **Spring Data JPA**: abstracción del acceso a datos, sin SQL manual
- **Contenedorización**: Dockerfile multistage + Docker Compose con red interna

---

## Tests

```bash
./mvnw test
```

---

## Licencia

MIT — libre para usar, modificar y distribuir.
