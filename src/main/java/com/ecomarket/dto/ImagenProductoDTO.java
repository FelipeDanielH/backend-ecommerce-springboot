package com.ecomarket.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImagenProductoDTO {
    private Integer id;
    private Integer productoId;
    private String urlImagen;
}
