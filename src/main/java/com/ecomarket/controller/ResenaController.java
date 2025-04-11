package com.ecomarket.controller;

import com.ecomarket.dto.resena.ResenaDTO;
import com.ecomarket.dto.resena.ResenaResponseDTO;
import com.ecomarket.service.ResenaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resenas")
@RequiredArgsConstructor
public class ResenaController {

    private final ResenaService resenaService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearResena(@RequestBody ResenaDTO dto) {
        resenaService.crearResena(dto);
        return ResponseEntity.ok("Reseña creada correctamente");
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<ResenaResponseDTO>> obtenerResenas(@PathVariable Integer productoId) {
        return ResponseEntity.ok(resenaService.obtenerResenasPorProducto(productoId));
    }
}