package com.ecomarket.dto.resena;

import lombok.Data;

@Data
public class ResenaDTO {
    private Integer productoId;
    private Integer usuarioId;
    private Integer calificacion;
    private String comentario;
}
