package com.innova.curseando.exception;

import com.innova.curseando.dto.ApiError;
import com.innova.curseando.dto.GenericResponse;
import com.innova.curseando.util.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<GenericResponse<ApiError>> handleNotFound(EntityNotFoundException ex) {
        ApiError error = new ApiError(ex.getMessage(), LocalDateTime.now(), null);
        return ApiResponse.notFound(error);
    }


    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<GenericResponse<ApiError>> handleIllegalState(IllegalStateException ex) {
        ApiError error = new ApiError(ex.getMessage(), LocalDateTime.now(), null);
        return ApiResponse.badRequest(error);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GenericResponse<ApiError>> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> detalles = new HashMap<>();
        ex.getConstraintViolations().forEach(v -> detalles.put(v.getPropertyPath().toString(), v.getMessage()));
        ApiError error = new ApiError("Error de validación", LocalDateTime.now(), detalles);
        return ApiResponse.badRequest(error);
    }


    /*@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errores = new HashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            errores.put(fe.getField(), fe.getDefaultMessage());
        }
        ApiError error = new ApiError("Error de validación", LocalDateTime.now(), errores);
        return ApiResponse.badRequest(error);
    }*/


    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse<ApiError>> handleAll(Exception ex) {
        ApiError error = new ApiError("Error interno", LocalDateTime.now(), Map.of("error", ex.getMessage()));
        return ApiResponse.internalServerError(error);
    }
}
