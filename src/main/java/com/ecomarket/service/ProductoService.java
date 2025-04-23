package com.ecomarket.service;

import com.ecomarket.dto.producto.ProductoDTO;
import com.ecomarket.dto.producto.VendedorNombreDTO;

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
    ProductoDTO actualizarStock(Integer id, Integer nuevoStock);
    VendedorNombreDTO obtenerNombreVendedorPorProductoId(Integer productoId);
}
