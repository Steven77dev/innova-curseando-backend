package com.innova.curseando.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
@Table(name = "CURSO")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @Lob
    private String descripcion;

    private String instructor;

    @NotNull
    private Integer duracion;

    @NotBlank
    private String nivel; // "principiante", "intermedio", "avanzado"

    @NotNull
    private Integer capacidad;

    @NotNull
    private Integer inscritos = 0;

    public Integer getDisponibles(){
        return capacidad - inscritos;
    }

    public boolean hayCupos(){
        if (this.inscritos == null) return this.capacidad != null && this.capacidad > 0;
        return this.capacidad == null || this.inscritos < this.capacidad;
    }

}
