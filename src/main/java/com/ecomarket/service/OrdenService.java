package com.ecomarket.service;

import com.ecomarket.dto.OrdenDTO;

import java.util.List;

public interface OrdenService {
    OrdenDTO crearOrden(OrdenDTO ordenDTO);
    OrdenDTO obtenerOrdenPorId(Integer id);
    List<OrdenDTO> obtenerOrdenesPorUsuario(Integer compradorId);
}
