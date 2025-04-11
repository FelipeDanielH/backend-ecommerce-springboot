package com.ecomarket.dto.favorito;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoritoDetalleDTO {
    private Integer productoId;
    private String nombreProducto;
    private String descripcionProducto;
    private BigDecimal precioProducto;
    private String fechaAgregado;
}