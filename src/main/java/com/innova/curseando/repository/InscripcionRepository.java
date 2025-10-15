package com.innova.curseando.repository;

import com.innova.curseando.model.entity.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    boolean existsByCursoIdAndEstudianteId(Long idCurso, Long idEstudiante);

}
