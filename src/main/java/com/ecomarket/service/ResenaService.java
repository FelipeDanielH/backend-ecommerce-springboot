// service/ResenaService.java
package com.ecomarket.service;

import com.ecomarket.dto.resena.ResenaDTO;
import com.ecomarket.dto.resena.ResenaResponseDTO;

import java.util.List;

public interface ResenaService {
    void crearResena(ResenaDTO dto);
    List<ResenaResponseDTO> obtenerResenasPorProducto(Integer productoId);
}
