# Imagen base para compilacion
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app

# Copiar el wrapper de maven y pom
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Dar permisos de ejecucion al wrapper
RUN chmod +x ./mvnw

# Descargar dependencias
RUN ./mvnw dependency:go-offline -B

# Copiar el codigo fuente
COPY src src

# Compilar el proyecto
RUN ./mvnw clean package -DskipTests

# Imagen base para ejecucion
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copiar el jar compilado
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto
EXPOSE 8080

# Ejecutar la aplicacion
ENTRYPOINT ["java", "-jar", "app.jar"]
