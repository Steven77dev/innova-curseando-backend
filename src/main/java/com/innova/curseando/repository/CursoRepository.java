package com.innova.curseando.repository;

import com.innova.curseando.model.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByNivelIgnoreCase(String nivel);
}