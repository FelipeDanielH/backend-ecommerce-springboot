package com.ecomarket.service;

import com.ecomarket.dto.ProductoDTO;
import java.util.List;

public interface ProductoService {
    List<ProductoDTO> obtenerTodos();
    ProductoDTO obtenerPorId(Integer id);
    List<ProductoDTO> buscarPorNombre(String nombre);
    List<ProductoDTO> obtenerPorVendedor(Integer vendedorId);
    List<ProductoDTO> obtenerPorCategoria(Integer categoriaId);
    List<ProductoDTO> buscarPorRangoPrecio(Double min, Double max);
    ProductoDTO guardarProducto(ProductoDTO productoDTO);
    void eliminarProducto(Integer id);
}
