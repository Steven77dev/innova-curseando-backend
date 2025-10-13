package com.innova.curseando.dto;

public record DetalleCursoDTO(Long id, String titulo, String instructor, Integer duracion, String nivel, Integer capacidad, Integer disponibles, String descripcion, Integer inscritos, boolean hayCupos) {}

