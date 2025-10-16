package com.innova.curseando.controller;

import com.innova.curseando.dto.GenericResponse;
import com.innova.curseando.model.entity.Inscripcion;
import com.innova.curseando.model.request.InscripcionRequest;
import com.innova.curseando.service.InscripcionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inscripcion")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @PostMapping
    public ResponseEntity<GenericResponse<Inscripcion>> inscribir(@Valid @RequestBody InscripcionRequest request) {
        GenericResponse<Inscripcion> response = inscripcionService.inscribir(
                request.getNombreCompleto(),
                request.getEmail(),
                request.getIdCurso()
        );
        return ResponseEntity.ok(response);
    }
}
