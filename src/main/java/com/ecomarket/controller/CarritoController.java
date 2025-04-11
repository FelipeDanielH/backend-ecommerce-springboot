package com.ecomarket.controller;

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

    @PutMapping("/{usuarioId}/producto/{productoId}")
    public ResponseEntity<Void> modificarCantidad(@PathVariable Integer usuarioId,
                                                  @PathVariable Integer productoId,
                                                  @RequestParam int cantidad) {
        carritoService.modificarCantidadProducto(usuarioId, productoId, cantidad);
        return ResponseEntity.ok().build();
    }

}
