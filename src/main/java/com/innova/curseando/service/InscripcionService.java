package com.innova.curseando.service;

import com.innova.curseando.dto.GenericResponse;
import com.innova.curseando.model.entity.Curso;
import com.innova.curseando.model.entity.Estudiante;
import com.innova.curseando.model.entity.Inscripcion;
import com.innova.curseando.repository.CursoRepository;
import com.innova.curseando.repository.EstudianteRepository;
import com.innova.curseando.repository.InscripcionRepository;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InscripcionService {

    private final CursoRepository cursoRepo;
    private final EstudianteRepository estudianteRepo;
    private final InscripcionRepository inscripcionRepo;

    public InscripcionService(CursoRepository cursoRepo,
                              EstudianteRepository estudianteRepo,
                              InscripcionRepository inscripcionRepo) {
        this.cursoRepo = cursoRepo;
        this.estudianteRepo = estudianteRepo;
        this.inscripcionRepo = inscripcionRepo;
    }

    @Transactional
    public GenericResponse<Inscripcion> inscribir(String nombreCompleto, String email, Long idCurso) {
        if (nombreCompleto == null || nombreCompleto.isBlank() || email == null || email.isBlank()) {
            return new GenericResponse<>(-1, "Todos los campos son obligatorios.", null);
        }

        Curso curso = cursoRepo.findById(idCurso).orElse(null);

        if (curso == null) {
            return new GenericResponse<>(-1, "Curso no encontrado.", null);
        }

        if (!curso.hayCupos()) {
            return new GenericResponse<>(-1, "El curso no tiene cupos disponibles.", null);
        }

        // Buscar o crear estudiante
        Estudiante estudiante = estudianteRepo.findByEmail(email)
                .orElseGet(() -> {
                    Estudiante e = new Estudiante();
                    e.setNombreCompleto(nombreCompleto);
                    e.setEmail(email);
                    return estudianteRepo.save(e);
                });

        // Verificar si ya está inscrito
        boolean yaInscrito = inscripcionRepo.existsByCursoIdAndEstudianteId(curso.getId(), estudiante.getId());
        if (yaInscrito) {
            return new GenericResponse<>(-1, "El estudiante ya está inscrito en este curso.", null);
        }

        // Crear inscripción
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setCurso(curso);
        inscripcion.setEstudiante(estudiante);
        inscripcionRepo.save(inscripcion);

        // Actualizar contador
        curso.incrementarInscritos();
        cursoRepo.save(curso);

        return new GenericResponse<>(200, "Inscripción realizada con éxito.", inscripcion);
    }
}