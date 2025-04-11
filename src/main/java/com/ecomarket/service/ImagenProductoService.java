package com.ecomarket.service;

import com.ecomarket.dto.ImagenProductoDTO;

import java.util.List;

public interface ImagenProductoService {
    ImagenProductoDTO crear(ImagenProductoDTO dto);
    ImagenProductoDTO obtenerPorId(Integer id);
    List<ImagenProductoDTO> listarTodos();
    ImagenProductoDTO actualizar(Integer id, ImagenProductoDTO dto);
    void eliminar(Integer id);

    List<ImagenProductoDTO> listarPorProductoId(Integer productoId);
}
