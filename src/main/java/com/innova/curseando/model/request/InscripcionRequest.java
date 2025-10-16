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

    @NotBlank(message = "El nombre no debe estar vacío")
    private String nombreCompleto;

    @Email(message = "Debe ingresar un correo válido")
    @NotBlank(message = "El correo no debe estar vacío")
    private String email;
    @NotNull(message = "El ID del curso es obligatorio")
    private Long idCurso;
}
