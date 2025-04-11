package com.ecomarket.dto.favorito;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoritoDTO {
    private Integer id;
    private Integer usuarioId;
    private Integer productoId;
    private String fechaAgregado;
}