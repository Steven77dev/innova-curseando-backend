package com.innova.curseando.exception;

import com.innova.curseando.dto.ApiError;
import com.innova.curseando.dto.GenericResponse;
import com.innova.curseando.util.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class ExceptionHelper  {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHelper.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<GenericResponse<ApiError>> handleNotFound(EntityNotFoundException ex) {
        ApiError error = new ApiError(ex.getMessage(), LocalDateTime.now(), null);
        return ApiResponse.notFound(error);
    }


    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<GenericResponse<ApiError>> handleIllegalState(IllegalStateException ex) {
        logger.error("IllegalStateException {}", ex.getMessage());
        ApiError error = new ApiError(ex.getMessage(), LocalDateTime.now(), null);
        return ApiResponse.badRequest(error);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GenericResponse<ApiError>> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> detalles = new HashMap<>();
        ex.getConstraintViolations().forEach(v -> detalles.put(v.getPropertyPath().toString(), v.getMessage()));
        logger.error("ConstraintViolationException {}", ex.getMessage());
        ApiError error = new ApiError("Error de validación", LocalDateTime.now(), detalles);
        return ApiResponse.badRequest(error);
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<GenericResponse<?>> handleOptimisticLock(OptimisticLockingFailureException ex) {
        logger.error("OptimisticLockingFailureException {}", ex.getMessage());
        GenericResponse<?> response = new GenericResponse<>(-1, "Conflicto de concurrencia: intente nuevamente", null);
        return ResponseEntity.status(409).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }
        logger.error("MethodArgumentNotValidException {}", ex.getMessage());
        GenericResponse<?> apiResponse =new GenericResponse(-1, "Se presentó un error en las validaciones. Consultar con Soporte.",errors);
        return ResponseEntity.status(400).body(apiResponse);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<GenericResponse<?>> handleUnexpectedTypeException(UnexpectedTypeException ex) {
        logger.error("UnexpectedTypeException {}", ex.getMessage());
        GenericResponse<?> apiResponse =new GenericResponse(-1, "Se presentó tipo de dato no válido para la restricción usada",null);
        return ResponseEntity.status(400).body(apiResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse<?>> handleException(Exception ex) {
        GenericResponse<?> apiResponse =new GenericResponse(-1, "Se presentó un error en el servidor. Consultar con Soporte.",null);
        return ResponseEntity.status(500).body(apiResponse);
    }
}
