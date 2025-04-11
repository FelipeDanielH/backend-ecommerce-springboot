package com.ecomarket.mapper;

import com.ecomarket.dto.ImagenProductoDTO;
import com.ecomarket.model.ImagenProducto;
import org.springframework.stereotype.Component;

@Component
public class ImagenProductoMapper {
    public ImagenProductoDTO toDTO(ImagenProducto entity) {
        return ImagenProductoDTO.builder()
                .id(entity.getId())
                .productoId(entity.getProductoId())
                .urlImagen(entity.getUrlImagen())
                .build();
    }

    public ImagenProducto toEntity(ImagenProductoDTO dto) {
        return ImagenProducto.builder()
                .id(dto.getId())
                .productoId(dto.getProductoId())
                .urlImagen(dto.getUrlImagen())
                .build();
    }
}
