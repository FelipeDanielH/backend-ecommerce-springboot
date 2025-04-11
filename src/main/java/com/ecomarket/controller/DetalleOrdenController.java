package com.ecomarket.controller;

import com.ecomarket.dto.DetalleOrdenDTO;
import com.ecomarket.model.DetalleOrden;
import com.ecomarket.service.DetalleOrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalles-orden")
@RequiredArgsConstructor
public class DetalleOrdenController {

    private final DetalleOrdenService detalleOrdenService;

    @GetMapping("/{ordenId}")
    public ResponseEntity<List<DetalleOrdenDTO>> obtenerDetalles(@PathVariable Integer ordenId) {
        return ResponseEntity.ok(detalleOrdenService.obtenerDetallesPorOrdenId(ordenId));
    }

    @PostMapping
    public ResponseEntity<DetalleOrdenDTO> guardarDetalle(@RequestBody DetalleOrdenDTO detalleOrdenDTO) {
        return ResponseEntity.ok(detalleOrdenService.guardarDetalleOrden(detalleOrdenDTO));
    }
}
