package com.ecomarket.controller;

import com.ecomarket.dto.OrdenDTO;
import com.ecomarket.service.OrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenes")
@RequiredArgsConstructor
public class OrdenController {

    private final OrdenService ordenService;

    @PostMapping
    public ResponseEntity<OrdenDTO> crearOrden(@RequestBody OrdenDTO ordenDTO) {
        OrdenDTO nuevaOrden = ordenService.crearOrden(ordenDTO);
        return ResponseEntity.ok(nuevaOrden);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenDTO> obtenerOrdenPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(ordenService.obtenerOrdenPorId(id));
    }

    @GetMapping("/usuario/{compradorId}")
    public ResponseEntity<List<OrdenDTO>> obtenerOrdenesPorUsuario(@PathVariable Integer compradorId) {
        return ResponseEntity.ok(ordenService.obtenerOrdenesPorUsuario(compradorId));
    }


}
