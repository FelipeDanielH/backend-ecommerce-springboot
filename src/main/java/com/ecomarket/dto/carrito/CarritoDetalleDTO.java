package com.ecomarket.dto.carrito;

import com.ecomarket.dto.ProductoDTO;
import lombok.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

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