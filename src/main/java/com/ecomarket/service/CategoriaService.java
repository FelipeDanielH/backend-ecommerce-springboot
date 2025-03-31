package com.ecomarket.service;

import com.ecomarket.dto.CategoriaDTO;

import java.util.List;

public interface CategoriaService {
    List<CategoriaDTO> listarCategorias();
    CategoriaDTO obtenerCategoriaPorId(Integer id);
    CategoriaDTO crearCategoria(CategoriaDTO categoriaDTO);
    CategoriaDTO actualizarCategoria(Integer id, CategoriaDTO categoriaDTO);
    void eliminarCategoria(Integer id);
}
