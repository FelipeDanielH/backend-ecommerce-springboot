package com.ecomarket.dto.producto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarStockRequest {
    private Integer stock;
}

