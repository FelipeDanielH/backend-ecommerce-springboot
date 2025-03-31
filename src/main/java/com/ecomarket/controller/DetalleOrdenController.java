package com.ecomarket.controller;

import com.ecomarket.model.DetalleOrden;
import com.ecomarket.service.DetalleOrdenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-orden")
public class DetalleOrdenController {

    private final DetalleOrdenService detalleOrdenService;

    public DetalleOrdenController(DetalleOrdenService detalleOrdenService) {
        this.detalleOrdenService = detalleOrdenService;
    }

    @GetMapping("/{ordenId}")
    public ResponseEntity<List<DetalleOrden>> obtenerDetallesPorOrden(@PathVariable Integer ordenId) {
        return ResponseEntity.ok(detalleOrdenService.obtenerDetallesPorOrdenId(ordenId));
    }

    @PostMapping
    public ResponseEntity<DetalleOrden> agregarDetalleOrden(@RequestBody DetalleOrden detalleOrden) {
        return ResponseEntity.ok(detalleOrdenService.guardarDetalle(detalleOrden));
    }
}
