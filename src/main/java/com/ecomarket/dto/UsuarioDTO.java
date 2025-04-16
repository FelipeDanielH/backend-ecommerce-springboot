package com.ecomarket.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
    private Integer id;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private String tipo;
}