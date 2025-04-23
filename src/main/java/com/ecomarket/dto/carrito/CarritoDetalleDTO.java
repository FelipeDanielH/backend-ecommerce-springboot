package com.ecomarket.dto.carrito;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoDetalleDTO {
    private Integer productoId;
    private String productoNombre;
    private Integer cantidad;
    private Double precioUnitario;
}