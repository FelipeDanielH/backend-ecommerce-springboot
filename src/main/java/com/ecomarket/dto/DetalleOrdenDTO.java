package com.ecomarket.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleOrdenDTO {
    private Integer id;
    private Integer ordenId;
    private Integer productoId;
    private String nombreProducto;
    private int cantidad;
    private double precioUnitario;

    private Integer vendedorId;
    private String vendedorNombre;
}