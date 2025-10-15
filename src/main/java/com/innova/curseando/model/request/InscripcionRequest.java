package com.innova.curseando.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionRequest {

    @NotBlank
    private String nombreCompleto;
    @Email
    @NotBlank
    private String email;
    @NotNull(message = "El ID del curso es obligatorio")
    private Long idCurso;
}
