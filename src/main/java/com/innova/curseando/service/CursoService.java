package com.innova.curseando.service;

import com.innova.curseando.dto.CursoDTO;
import com.innova.curseando.dto.DetalleCursoDTO;
import com.innova.curseando.model.entity.Curso;
import com.innova.curseando.repository.CursoRepository;
import com.innova.curseando.util.EnumNiveles;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CursoService {
    private final CursoRepository cursoRepository;

    public List<CursoDTO> listar(Integer nivelCodigo) {
        List<CursoDTO> cursos;

        if (nivelCodigo != null) {
            EnumNiveles nivelEnum = EnumNiveles.fromCodigo(nivelCodigo);
            cursos = filtrarPorNivel(nivelEnum.getDescripcion());
        } else {
            cursos = listarTodos();
        }

        return cursos;
    }

    public List<CursoDTO> listarTodos() {
        return cursoRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    private List<CursoDTO> filtrarPorNivel(String nivel) {
        return cursoRepository.findByNivelIgnoreCase(nivel).stream().map(this::toDto).collect(Collectors.toList());
    }

    public DetalleCursoDTO obtener(Long id) {
        Curso c = cursoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));
        return detalleToDto(c);
    }


    private CursoDTO toDto(Curso c) {
        return new CursoDTO(
                c.getId(),
                c.getTitulo(),
                c.getInstructor(),
                c.getDuracion(),
                c.getNivel(),
                c.getCapacidad(),
                c.getDisponibles()
        );
    }

    private DetalleCursoDTO detalleToDto(Curso c) {
        return new DetalleCursoDTO(
                c.getId(),
                c.getTitulo(),
                c.getInstructor(),
                c.getDuracion(),
                c.getNivel(),
                c.getCapacidad(),
                c.getDisponibles(),
                c.getDescripcion(),
                c.getInscritos(),
                c.hayCupos()
        );
    }
}