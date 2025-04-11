package com.ecomarket.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleOrdenDTO {
    private Integer id;
    private Integer ordenId;
    private Integer productoId;
    private String nombreProducto;
    private int cantidad;
    private double precioUnitario;
}
