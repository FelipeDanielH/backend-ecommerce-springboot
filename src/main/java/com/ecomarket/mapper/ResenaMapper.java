// mapper/ResenaMapper.java
package com.ecomarket.mapper;

import com.ecomarket.dto.resena.ResenaResponseDTO;
import com.ecomarket.model.Resena;
import org.springframework.stereotype.Component;

@Component
public class ResenaMapper {

    public ResenaResponseDTO toDTO(Resena resena) {
        return ResenaResponseDTO.builder()
                .id(resena.getId())
                .calificacion(resena.getCalificacion())
                .comentario(resena.getComentario())
                .fecha(resena.getFecha())
                .nombreUsuario(resena.getUsuario().getNombre()) // Asumiendo que Usuario tiene getNombre()
                .build();
    }
}
