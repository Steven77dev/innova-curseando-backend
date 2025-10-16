CREATE DATABASE curseando;

CREATE TABLE curso (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    duracion INT NOT NULL,
    nivel VARCHAR(50) NOT NULL CHECK (nivel IN ('principiante', 'intermedio', 'avanzado')),
    capacidad INT NOT NULL CHECK (capacidad > 0),
    inscritos INT DEFAULT 0,
    descripcion TEXT,
    instructor VARCHAR(100) NOT NULL,
	version BIGINT DEFAULT 0 NOT NULL
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
    estado VARCHAR(20) DEFAULT 'ACTIVA' CHECK (estado IN ('ACTIVA', 'CERRADA')),
    UNIQUE (curso_id, estudiante_id)
);

-- PRINCIPIANTE
INSERT INTO CURSO (TITULO, DURACION, NIVEL, CAPACIDAD, INSCRITOS, DESCRIPCION, INSTRUCTOR, VERSION) VALUES
('Introducción a Java',40,'principiante',5,0,'Aprende fundamentos de Java y programación orientada a objetos.','Luis Perez',0);

INSERT INTO CURSO (TITULO, DURACION, NIVEL, CAPACIDAD, INSCRITOS, DESCRIPCION, INSTRUCTOR,VERSION) VALUES
('Fundamentos de HTML, CSS y JavaScript',30,'principiante',6,0,'Domina las bases del desarrollo web front-end.','Carla Muñoz',0);

INSERT INTO CURSO (TITULO, DURACION, NIVEL, CAPACIDAD, INSCRITOS, DESCRIPCION, INSTRUCTOR,VERSION) VALUES
('Lógica de Programación con Python',40,'principiante',7,0,'Aprende a pensar como programador usando Python.','Jorge Torres',0);

-- INTERMEDIO
INSERT INTO CURSO (TITULO, DURACION, NIVEL, CAPACIDAD, INSCRITOS, DESCRIPCION, INSTRUCTOR,VERSION) VALUES
('Java con Spring Boot',30,'intermedio',10,0,'Construye microservicios modernos con Spring Boot 3.','Ana Gomez',0);

INSERT INTO CURSO (TITULO, DURACION, NIVEL, CAPACIDAD, INSCRITOS, DESCRIPCION, INSTRUCTOR,VERSION) VALUES
('Desarrollo Web con Angular y REST APIs',45,'intermedio',6,0,'Integra Angular con servicios REST y autenticación JWT.','Carlos Ruiz',0);

INSERT INTO CURSO (TITULO, DURACION, NIVEL, CAPACIDAD, INSCRITOS, DESCRIPCION, INSTRUCTOR,VERSION) VALUES
('Docker y Kubernetes para Desarrolladores',40,'intermedio',6,0,'Aprende a contenerizar y desplegar tus aplicaciones con Docker y K8s.','María Flores',0);

-- AVANZADO
INSERT INTO CURSO (TITULO, DURACION, NIVEL, CAPACIDAD, INSCRITOS, DESCRIPCION, INSTRUCTOR,VERSION) VALUES
('Angular Avanzado',50,'avanzado',8,0,'Profundiza en Angular 15, RxJS y arquitectura modular.','Carlos Ruiz',0);

INSERT INTO CURSO (TITULO, DURACION, NIVEL, CAPACIDAD, INSCRITOS, DESCRIPCION, INSTRUCTOR,VERSION) VALUES
('Microservicios con Spring Cloud y Kafka',55,'avanzado',5,0,'Implementa comunicación asíncrona y resiliente con Spring Cloud y Kafka.','Ana Gomez',0);

INSERT INTO CURSO (TITULO, DURACION, NIVEL, CAPACIDAD, INSCRITOS, DESCRIPCION, INSTRUCTOR,VERSION) VALUES
('Arquitectura Limpia y DDD en Java',60,'avanzado',6,0,'Aplica Domain-Driven Design y buenas prácticas de arquitectura.','Luis Perez',0);

INSERT INTO CURSO (TITULO, DURACION, NIVEL, CAPACIDAD, INSCRITOS, DESCRIPCION, INSTRUCTOR,VERSION) VALUES
('Machine Learning con Python y TensorFlow',50,'avanzado',8,0,'Crea modelos predictivos con Python, TensorFlow y scikit-learn.','Jorge Torres',0);
