package com.ecomarket.service;

import com.ecomarket.dto.carrito.CarritoDTO;

public interface CarritoService {
    CarritoDTO obtenerCarritoPorUsuario(Integer usuarioId);
    void eliminarProductoDelCarrito(Integer usuarioId, Integer productoId);
    void modificarCantidadProducto(Integer usuarioId, Integer productoId, int cantidad);
    CarritoDTO agregarProductoAlCarrito(Integer usuarioId, Integer productoId, Integer cantidad);
    void vaciarCarrito(Integer usuarioId);
}
