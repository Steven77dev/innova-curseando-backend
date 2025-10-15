package com.innova.curseando.model.entity;

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
    private String titulo;
    private String descripcion;
    private String instructor;
    private Integer duracion;
    private String nivel; // "principiante", "intermedio", "avanzado"
    private Integer capacidad;
    private Integer inscritos = 0;

    @Version
    private Long version;

    public Integer getDisponibles(){
        return capacidad - inscritos;
    }

    public boolean hayCupos(){
        return inscritos< capacidad;
    }

    public void incrementarInscritos() {
        if (this.inscritos == null) this.inscritos = 0;
        this.inscritos++;
    }

}
