package com.innova.curseando.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
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
    @Column(nullable = false)
    private Integer capacidad = 0;
    @Column(nullable = false)
    private Integer inscritos = 0;

    @Version
    @Setter(AccessLevel.NONE)
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
