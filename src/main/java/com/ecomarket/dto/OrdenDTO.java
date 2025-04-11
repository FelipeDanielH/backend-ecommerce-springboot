package com.ecomarket.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenDTO {
    private Integer id;
    private Integer compradorId;
    private BigDecimal total;
    private String estado;
    private List<DetalleOrdenDTO> detalles;
    private LocalDateTime fechaOrden;
}
