package com.ecomarket.dto.carrito;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoActualizarCantidadRequest {
    private Integer productoId;
    private Integer cantidad;
}