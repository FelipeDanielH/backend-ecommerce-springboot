package com.ecomarket.dto.carrito;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoAgregarProductoRequest {
    private Integer productoId;
    private Integer cantidad;
}