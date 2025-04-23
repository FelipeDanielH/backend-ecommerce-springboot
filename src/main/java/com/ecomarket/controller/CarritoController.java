package com.ecomarket.controller;

import com.ecomarket.dto.carrito.CarritoActualizarCantidadRequest;
import com.ecomarket.dto.carrito.CarritoAgregarProductoRequest;
import com.ecomarket.dto.carrito.CarritoDTO;
import com.ecomarket.service.CarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;

    @GetMapping("/{usuarioId}")
    public ResponseEntity<CarritoDTO> obtenerCarrito(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(carritoService.obtenerCarritoPorUsuario(usuarioId));
    }

    @DeleteMapping("/{usuarioId}/producto/{productoId}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer usuarioId, @PathVariable Integer productoId) {
        carritoService.eliminarProductoDelCarrito(usuarioId, productoId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{usuarioId}/producto")
    public ResponseEntity<Void> modificarCantidad(
            @PathVariable Integer usuarioId,
            @RequestBody CarritoActualizarCantidadRequest request) {
        carritoService.modificarCantidadProducto(usuarioId, request.getProductoId(), request.getCantidad());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{usuarioId}/agregar")
    public ResponseEntity<CarritoDTO> agregarProducto(
            @PathVariable Integer usuarioId,
            @RequestBody CarritoAgregarProductoRequest request) {
        CarritoDTO carritoActualizado = carritoService.agregarProductoAlCarrito(usuarioId, request.getProductoId(), request.getCantidad());
        return ResponseEntity.ok(carritoActualizado);
    }

    @DeleteMapping("/{usuarioId}/vaciar")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable Integer usuarioId) {
        carritoService.vaciarCarrito(usuarioId);
        return ResponseEntity.noContent().build();
    }
}