# Rapidito

Sistema de gestión de alquiler de vehículos desarrollado con **Java 17 + Spring Boot 4**.

Arquitectura dual: API REST (`/api/*`) + aplicación web MVC (`/*`) que consume la API internamente mediante `RestClient`.

---

## Arquitectura

```
┌─────────────────────────────────────────────┐
│              Spring Boot App                │
│                                             │
│  8080/          →  Web MVC (Thymeleaf)      │
│                    └─ RestClient            │
│                         └─ 8080/api/*  ─┐   │
│  8080/api/*     →  REST API          ←──┘   │
│                    └─ JPA / MySQL           │
└─────────────────────────────────────────────┘
```

La capa web **no accede directamente a la base de datos** — delega toda la lógica en la API REST a través de `RestClient`, simulando una arquitectura de microservicios desacoplada.

---

## Dominio

| Entidad | Descripción |
|---|---|
| `Vehiculo` | Flota disponible (marca, modelo, matrícula, precio/día) |
| `Cliente` | Datos del arrendatario |
| `Reserva` | Reserva de vehículo por cliente y fechas |
| `Contrato` | Contrato de alquiler activo |

Relaciones: `Vehiculo` → `*Reserva`, `Vehiculo` → `*Contrato`.

---

## Stack tecnológico

| Capa | Tecnología |
|---|---|
| Lenguaje | Java 17 |
| Framework | Spring Boot 4.0.6 |
| API REST | Spring MVC (`@RestController`, `ResponseEntity`) |
| Cliente HTTP | Spring `RestClient` (Spring 6.1+) |
| Persistencia | Spring Data JPA + Hibernate (Jakarta EE 9) |
| Base de datos | MySQL 8 + HikariCP |
| Mapeo DTO | MapStruct 1.6 |
| Reducción boilerplate | Lombok 1.18 |
| Validación | Spring Boot Validation (Bean Validation 3) |
| Vistas | Thymeleaf |
| Build | Maven (wrapper incluido) |
| Contenedores | Docker + Docker Compose |
| CI | GitHub Actions |

---

## Endpoints API REST

### Vehículos `/api/vehiculos`

| Método | Ruta | Descripción |
|---|---|---|
| `GET` | `/` | Listar todos |
| `GET` | `/{id}` | Ver detalle |
| `GET` | `/{marca}/list` | Filtrar por marca |
| `PUT` | `/` | Crear |
| `PATCH` | `/{id}/edit` | Actualizar |
| `DELETE` | `/{id}/delete` | Eliminar |

Misma estructura para `/api/clientes`, `/api/reservas`, `/api/contratos`.

---

## Ejecución local

### Sin Docker

```bash
git clone https://github.com/Medgasan/rapidito.git
cd rapidito

# Crear base de datos MySQL
mysql -u root -e "CREATE DATABASE rapidito;"

# Ejecutar
./mvnw spring-boot:run
```

- Web: `http://localhost:8080`
- API: `http://localhost:8080/api/vehiculos/`

### Con Docker Compose (recomendado)

```bash
git clone https://github.com/Medgasan/rapidito.git
cd rapidito
docker compose up --build
```

Servicios levantados:
- Web + API: `http://localhost:8080`
- MySQL: `localhost:3306`

---

## Estructura del proyecto

```
src/main/java/.../rapidito/
├── RapiditoApplication.java
├── RestConfig.java              # Bean RestClient con baseUrl configurable
├── api/
│   ├── controller/              # @RestController — endpoints JSON
│   ├── dto/                     # Data Transfer Objects
│   ├── entity/                  # @Entity JPA
│   ├── mappers/                 # MapStruct (Entity <-> DTO)
│   ├── repository/              # Spring Data JPA
│   └── service/                 # Interfaz + implementación
└── web/
    └── controller/              # @Controller — consume API via RestClient
```

---

## Patrones aplicados

- **Separación API / Web**: la capa web consume la API como cliente HTTP externo, sin acceso directo a JPA
- **DTO + MapStruct**: desacoplamiento entre modelo de persistencia y contrato de API
- **Constructor injection**: sin `@Autowired` en campos
- **Interface + implementación**: en capa de servicio (`IVehiculoService` / `VehiculoService`)
- **RestClient moderno**: Spring 6.1 — `ParameterizedTypeReference` para colecciones genéricas
- **HikariCP configurado**: pool size, connection timeout, idle timeout, max lifetime
- **Jakarta EE 9**: `jakarta.persistence.*`

---

## Tests

```bash
./mvnw test
```

---

## Licencia

MIT