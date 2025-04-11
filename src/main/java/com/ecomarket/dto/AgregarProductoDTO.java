package com.ecomarket.dto;

import lombok.Data;

@Data
public class AgregarProductoDTO {
    private Integer usuarioId;
    private Integer productoId;
    private Integer cantidad;
}