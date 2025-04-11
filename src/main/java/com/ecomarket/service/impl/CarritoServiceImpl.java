package com.ecomarket.service.impl;

import com.ecomarket.dto.carrito.*;
import com.ecomarket.model.Carrito;
import com.ecomarket.model.CarritoDetalle;
import com.ecomarket.model.Usuario;
import com.ecomarket.repository.CarritoRepository;
import com.ecomarket.repository.CarritoDetalleRepository;
import com.ecomarket.repository.ProductoRepository;
import com.ecomarket.repository.UsuarioRepository;
import com.ecomarket.service.CarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarritoServiceImpl implements CarritoService {

    private final CarritoRepository carritoRepository;
    private final CarritoDetalleRepository carritoDetalleRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public CarritoDTO obtenerCarritoPorUsuario(Integer usuarioId) {
        Carrito carrito = carritoRepository.findByUsuarioId(usuarioId)
                .orElseGet(() -> {
                    Usuario usuario = usuarioRepository.findById(usuarioId)
                            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                    return carritoRepository.save(Carrito.builder().usuario(usuario).build());
                });

        List<CarritoDetalleDTO> detalles = carrito.getDetalles().stream()
                .map(detalle -> CarritoDetalleDTO.builder()
                        .productoId(detalle.getProducto().getId())
                        .productoNombre(detalle.getProducto().getNombre())
                        .cantidad(detalle.getCantidad())
                        .precioUnitario(detalle.getProducto().getPrecio().doubleValue())
                        .build())
                .collect(Collectors.toList());

        return CarritoDTO.builder()
                .id(carrito.getId())
                .usuarioId(usuarioId)
                .detalles(detalles)
                .build();
    }

    @Override
    public void eliminarProductoDelCarrito(Integer usuarioId, Integer productoId) {
        Carrito carrito = carritoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        carrito.getDetalles().removeIf(detalle -> detalle.getProducto().getId().equals(productoId));
        carritoRepository.save(carrito);
    }

    @Override
    public void modificarCantidadProducto(Integer usuarioId, Integer productoId, int cantidad) {
        Carrito carrito = carritoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        CarritoDetalle detalle = carrito.getDetalles().stream()
                .filter(d -> d.getProducto().getId().equals(productoId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en el carrito"));

        detalle.setCantidad(detalle.getCantidad() + cantidad);
        carritoDetalleRepository.save(detalle);
    }
}
