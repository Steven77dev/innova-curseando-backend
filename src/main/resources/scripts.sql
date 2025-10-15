CREATE DATABASE curseando;

CREATE TABLE curso (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    duracion INT NOT NULL,
    nivel VARCHAR(50) NOT NULL CHECK (nivel IN ('principiante', 'intermedio', 'avanzado')),
    capacidad INT NOT NULL CHECK (capacidad > 0),
    inscritos INT DEFAULT 0,
    descripcion TEXT,
    instructor VARCHAR(100) NOT NULL
);

CREATE TABLE estudiante (
    id SERIAL PRIMARY KEY,
    nombre_completo VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE inscripcion (
    id SERIAL PRIMARY KEY,
    curso_id INT NOT NULL REFERENCES curso(id) ON DELETE CASCADE,
    estudiante_id INT NOT NULL REFERENCES estudiante(id) ON DELETE CASCADE,
    fecha_inscripcion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(20) DEFAULT 'ACTIVA' CHECK (estado IN ('ACTIVA', 'CANCELADA')),
    UNIQUE (curso_id, estudiante_id)
);

-- PRINCIPIANTE
INSERT INTO CURSO (ID, TITULO, DURACION, NIVEL, CAPACIDAD, INSCRITOS, DESCRIPCION, INSTRUCTOR) VALUES
(1,'Introducción a Java',40,'principiante',10,0,'Aprende fundamentos de Java y programación orientada a objetos.','Luis Perez');

INSERT INTO CURSO (ID, TITULO, DURACION, NIVEL, CAPACIDAD, INSCRITOS, DESCRIPCION, INSTRUCTOR) VALUES
(2,'Fundamentos de HTML, CSS y JavaScript',30,'principiante',10,0,'Domina las bases del desarrollo web front-end.','Carla Muñoz');

INSERT INTO CURSO (ID, TITULO, DURACION, NIVEL, CAPACIDAD, INSCRITOS, DESCRIPCION, INSTRUCTOR) VALUES
(3,'Lógica de Programación con Python',40,'principiante',30,0,'Aprende a pensar como programador usando Python.','Jorge Torres');

-- INTERMEDIO
INSERT INTO CURSO (ID, TITULO, DURACION, NIVEL, CAPACIDAD, INSCRITOS, DESCRIPCION, INSTRUCTOR) VALUES
(4,'Java con Spring Boot',30,'intermedio',10,0,'Construye microservicios modernos con Spring Boot 3.','Ana Gomez');

INSERT INTO CURSO (ID, TITULO, DURACION, NIVEL, CAPACIDAD, INSCRITOS, DESCRIPCION, INSTRUCTOR) VALUES
(5,'Desarrollo Web con Angular y REST APIs',45,'intermedio',15,0,'Integra Angular con servicios REST y autenticación JWT.','Carlos Ruiz');

INSERT INTO CURSO (ID, TITULO, DURACION, NIVEL, CAPACIDAD, INSCRITOS, DESCRIPCION, INSTRUCTOR) VALUES
(6,'Docker y Kubernetes para Desarrolladores',40,'intermedio',6,0,'Aprende a contenerizar y desplegar tus aplicaciones con Docker y K8s.','María Flores');

-- AVANZADO
INSERT INTO CURSO (ID, TITULO, DURACION, NIVEL, CAPACIDAD, INSCRITOS, DESCRIPCION, INSTRUCTOR) VALUES
(7,'Angular Avanzado',50,'avanzado',12,0,'Profundiza en Angular 15, RxJS y arquitectura modular.','Carlos Ruiz');

INSERT INTO CURSO (ID, TITULO, DURACION, NIVEL, CAPACIDAD, INSCRITOS, DESCRIPCION, INSTRUCTOR) VALUES
(8,'Microservicios con Spring Cloud y Kafka',55,'avanzado',5,0,'Implementa comunicación asíncrona y resiliente con Spring Cloud y Kafka.','Ana Gomez');

INSERT INTO CURSO (ID, TITULO, DURACION, NIVEL, CAPACIDAD, INSCRITOS, DESCRIPCION, INSTRUCTOR) VALUES
(9,'Arquitectura Limpia y DDD en Java',60,'avanzado',6,0,'Aplica Domain-Driven Design y buenas prácticas de arquitectura.','Luis Perez');

INSERT INTO CURSO (ID, TITULO, DURACION, NIVEL, CAPACIDAD, INSCRITOS, DESCRIPCION, INSTRUCTOR) VALUES
(10,'Machine Learning con Python y TensorFlow',50,'avanzado',8,0,'Crea modelos predictivos con Python, TensorFlow y scikit-learn.','Jorge Torres');
