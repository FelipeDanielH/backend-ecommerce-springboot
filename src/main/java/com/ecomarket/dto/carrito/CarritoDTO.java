package com.ecomarket.dto.carrito;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoDTO {
    private Integer id;
    private Integer usuarioId;
    private List<CarritoDetalleDTO> detalles;
}