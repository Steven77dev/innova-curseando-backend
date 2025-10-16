package com.innova.curseando.service;

import com.innova.curseando.dto.GenericResponse;
import com.innova.curseando.model.entity.Curso;
import com.innova.curseando.model.entity.Estudiante;
import com.innova.curseando.model.entity.Inscripcion;
import com.innova.curseando.repository.CursoRepository;
import com.innova.curseando.repository.EstudianteRepository;
import com.innova.curseando.repository.InscripcionRepository;
import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InscripcionService {

    private final CursoRepository cursoRepo;
    private final EstudianteRepository estudianteRepo;
    private final InscripcionRepository inscripcionRepo;

    @Transactional
    public GenericResponse<Inscripcion> inscribir(String nombreCompleto, String email, Long idCurso) {
        if (esCampoVacio(nombreCompleto) || esCampoVacio(email)) {
            return new GenericResponse<>(-1, "Todos los campos son obligatorios.", null);
        }

        try {

            Curso curso = cursoRepo.findByIdForUpdate(idCurso).orElse(null);
            if (curso == null) {
                return new GenericResponse<>(-1, "Curso no encontrado.", null);
            }

            if (!curso.hayCupos()) {
                return new GenericResponse<>(-1, "El curso no tiene cupos disponibles.", null);
            }

            Estudiante estudiante = obtenerORegistrarEstudiante(nombreCompleto, email);

            // Verificar si ya está inscrito
            boolean yaInscrito = inscripcionRepo.existsByCursoIdAndEstudianteId(curso.getId(), estudiante.getId());
            if (yaInscrito) {
                return new GenericResponse<>(-1, "El estudiante ya está inscrito en este curso.", null);
            }

            Inscripcion inscripcion = registrarInscripcion(curso, estudiante);
            actualizarCupos(curso);

            return new GenericResponse<>(200, "Inscripción realizada con éxito.", inscripcion);
        } catch (ObjectOptimisticLockingFailureException | OptimisticLockException e) {
            return new GenericResponse<>(-1, "Otro usuario está inscribiendo al mismo tiempo. Intente nuevamente.", null);
        }
    }

    private boolean esCampoVacio(String valor) {
        return valor == null || valor.isBlank();
    }

    private Estudiante obtenerORegistrarEstudiante(String nombreCompleto, String email) {
        return estudianteRepo.findByEmail(email)
                .orElseGet(() -> {
                    Estudiante nuevo = new Estudiante();
                    nuevo.setNombreCompleto(nombreCompleto);
                    nuevo.setEmail(email);
                    return estudianteRepo.save(nuevo);
                });
    }

    private Inscripcion registrarInscripcion(Curso curso, Estudiante estudiante) {
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setCurso(curso);
        inscripcion.setEstudiante(estudiante);
        return inscripcionRepo.save(inscripcion);
    }

    private void actualizarCupos(Curso curso) {
        curso.incrementarInscritos();
        cursoRepo.saveAndFlush(curso);
    }
}