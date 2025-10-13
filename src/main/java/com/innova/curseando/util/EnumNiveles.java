package com.innova.curseando.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumNiveles {
    PRINCIPIANTE(1, "Principiante"),
    INTERMEDIO(2, "Intermedio"),
    AVANZADO(3, "Avanzado");

    private final int codigo;
    private final String descripcion;

    public static EnumNiveles fromCodigo(int codigo) {
        for (EnumNiveles nivel : values()) {
            if (nivel.getCodigo() == codigo) {
                return nivel;
            }
        }
        throw new IllegalArgumentException("Código de nivel inválido: " + codigo);
    }
}
