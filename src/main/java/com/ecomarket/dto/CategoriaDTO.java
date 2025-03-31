package com.ecomarket.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer categoriaPadreId; // ID de la categoría padre
}
