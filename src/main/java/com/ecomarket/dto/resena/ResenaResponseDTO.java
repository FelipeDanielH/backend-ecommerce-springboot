// dto/ResenaResponseDTO.java
package com.ecomarket.dto.resena;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResenaResponseDTO {
    private Integer id;
    private Integer calificacion;
    private String comentario;
    private LocalDateTime fecha;

    private String nombreUsuario;
}
