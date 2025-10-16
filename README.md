# Curseando - Backend

Este módulo contiene la API REST desarrollada con **Spring Boot 3**, **Java 17**, **PostgreSQL** y **JPA (Hibernate)**.  
Proporciona los servicios de gestión de cursos, estudiantes e inscripciones, incluyendo control de concurrencia optimista y validaciones.

## Requisitos previos
- **Java 17+**
- **Maven**
- **PostgreSQL**

## Configuración inicial

1. Clona el repositorio:
   ```bash
   git clone https://github.com/Steven77dev/innova-curseando-backend.git
   cd innova-curseando-frontend
   ```

2. Configura el archivo `application.properties` con tus credenciales de base de datos.

3. Ejecuta los scripts SQL ubicados en `/resources/scripts.sql`.

## Ejecución del proyecto

### Maven
```bash
mvn spring-boot:run
```
### IDE
Ejecuta la clase principal: `com.innova.curseando.CurseandoApplication`  
URL por defecto: `http://localhost:8080`

## Pruebas concurrentes
```bash
mvn test -Dtest=InscripcionConcurrenteTest
```

## 👨‍💻 Autor
**Steven Llaja**  
Desarrollador Full Stack
📧 steven77dev@gmail.com

