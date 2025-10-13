package com.innova.curseando.controller;

import com.innova.curseando.dto.CursoDTO;
import com.innova.curseando.dto.DetalleCursoDTO;
import com.innova.curseando.dto.GenericResponse;
import com.innova.curseando.service.CursoService;
import com.innova.curseando.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "*")
public class CursoController {
    private final CursoService service;

    public CursoController(CursoService service) { this.service = service; }


    @GetMapping
    public ResponseEntity<GenericResponse<List<CursoDTO>>> listar(@RequestParam(value = "nivel", required = false) Integer nivel) {
        List<CursoDTO> datos = service.listar(nivel);
        return ApiResponse.ok(datos, "Cursos listados");
    }


    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<DetalleCursoDTO>> detalle(@PathVariable Long id) {
        DetalleCursoDTO dto = service.obtener(id);
        return ApiResponse.ok(dto, "Detalle del curso");
    }


    @PostMapping("/{id}/inscribir")
    public ResponseEntity<GenericResponse<CursoDTO>> inscribir(@PathVariable Long id) {
        CursoDTO actualizado = service.inscribir(id);
        return ApiResponse.ok(actualizado, "Inscripción realizada");
    }
}
