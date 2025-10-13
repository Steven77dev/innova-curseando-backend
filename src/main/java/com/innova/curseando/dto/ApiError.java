package com.innova.curseando.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;


@Getter
@Setter
public class ApiError {
    private String mensaje;
    private LocalDateTime timestamp;
    private Map<String, String> detalles; // campo->mensaje para validaciones


    public ApiError() {}


    public ApiError(String mensaje, LocalDateTime timestamp, Map<String, String> detalles) {
        this.mensaje = mensaje;
        this.timestamp = timestamp;
        this.detalles = detalles;
    }

}
