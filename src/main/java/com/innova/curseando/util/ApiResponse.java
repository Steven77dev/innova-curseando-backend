package com.innova.curseando.util;

import com.innova.curseando.dto.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ApiResponse {


    public static <T> ResponseEntity<GenericResponse<T>> ok(T data, String mensaje) {
        GenericResponse<T> body = new GenericResponse<>(HttpStatus.OK.value(), mensaje, data);
        return ResponseEntity.ok(body);
    }


    public static <T> ResponseEntity<GenericResponse<T>> badRequest(T data) {
        GenericResponse<T> body = new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), "Solicitud inválida", data);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }


    public static <T> ResponseEntity<GenericResponse<T>> notFound(T data) {
        GenericResponse<T> body = new GenericResponse<>(HttpStatus.NOT_FOUND.value(), "No encontrado", data);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }


    public static <T> ResponseEntity<GenericResponse<T>> internalServerError(T data) {
        GenericResponse<T> body = new GenericResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error interno", data);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}