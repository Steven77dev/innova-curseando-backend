package com.innova.curseando.repository;

import com.innova.curseando.model.entity.Curso;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM Curso c WHERE c.id = :id")
    Optional<Curso> findByIdForUpdate(@Param("id") Long id);

    List<Curso> findByNivelIgnoreCase(String nivel);
}