# ── Etapa 1: Build ──────────────────────────────────────────
FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /app

# Copiar wrapper y pom primero (aprovecha caché de Docker)
COPY mvnw pom.xml ./
COPY .mvn .mvn

# Descargar dependencias (cacheado si pom.xml no cambia)
RUN ./mvnw dependency:go-offline -q

# Copiar código fuente y compilar
COPY src ./src
RUN ./mvnw clean package -DskipTests -q

# ── Etapa 2: Runtime ─────────────────────────────────────────
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Usuario no-root por seguridad
RUN addgroup -S spring && adduser -S spring -G spring
USER spring

# Copiar JAR desde etapa de build
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
