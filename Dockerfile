# Usa una imagen con Maven para la etapa de construcción
FROM maven:3.8.6-eclipse-temurin-17 AS builder

# Directorio de trabajo
WORKDIR /app

# Copia primero el POM (esto permite cachear dependencias)
COPY pom.xml .

# Descarga dependencias (se cachean si el POM no cambia)
RUN mvn dependency:go-offline

# Copia todo el código fuente
COPY src src

# Compila el proyecto y crea el JAR
RUN mvn package -DskipTests

# Segunda etapa: imagen final más pequeña
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copia el JAR desde la etapa de construcción
COPY --from=builder /app/target/eco-market-backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=cloud"]